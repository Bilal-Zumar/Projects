import com.dtcc.smo.apisdk.Security;
import com.dtcc.smo.apisdk.SecurityCollection;
import com.dtcc.smo.apisdk.exception.ClientError;
import com.dtcc.smo.apisdk.exception.SmoApiException;
import com.dtcc.smo.apisdk.model.*;
import com.dtcc.smo.apisdk.request.SecurityPersistRequest;
import com.dtcc.smo.apisdk.request.SecurityRequest;
import com.dtcc.smo.entities.VdmFrbEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VdmFrbSecurityServiceTest {

    @Mock
    private Logger logger;

    @Mock
    private SecurityCollection securityCollection;

    @Mock
    private VdmFrbService vdmFrbService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private VdmFrbSecurityService vdmFrbSecurityService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void process_shouldPersistSecuritiesToSMO() throws SmoApiException, ClientError {
        // Arrange
        List<VdmFrbEntity> vdmFrbEntities = Arrays.asList(
                createVdmFrbEntity("CUSIP1"),
                createVdmFrbEntity("CUSIP2")
        );
        Set<Security> existingSecurities = new HashSet<>();
        List<Security> toPersist = Arrays.asList(
                createSecurity("CUSIP1"),
                createSecurity("CUSIP2")
        );
        SecurityPersistRequest persistRequest = new SecurityPersistRequest(toPersist);

        when(vdmFrbService.fetchVdmFrbEntityList()).thenReturn(vdmFrbEntities);
        when(vdmFrbSecurityService.checkExistingSecurities(vdmFrbEntities)).thenReturn(existingSecurities);
        when(vdmFrbSecurityService.createOrUpdateVdmFrbSecurity(any(VdmFrbEntity.class), anySet()))
                .thenReturn(createSecurity("CUSIP1"), createSecurity("CUSIP2"));

        // Act
        vdmFrbSecurityService.process();

        // Assert
        verify(vdmFrbService).fetchVdmFrbEntityList();
        verify(vdmFrbSecurityService).checkExistingSecurities(vdmFrbEntities);
        verify(vdmFrbSecurityService, times(2)).createOrUpdateVdmFrbSecurity(any(VdmFrbEntity.class), anySet());
        verify(vdmFrbSecurityService).persistSecuritiestoSMO(toPersist);
        verify(securityCollection).persist(persistRequest);
        verify(logger, times(2)).info(anyString());
    }

    @Test
    void checkExistingSecurities_shouldReturnExistingSecurities() throws SmoApiException {
        // Arrange
        List<VdmFrbEntity> internalSecurities = Arrays.asList(
                createVdmFrbEntity("CUSIP1"),
                createVdmFrbEntity("CUSIP2")
        );
        Set<String> identifiers = new HashSet<>(Arrays.asList("CUSIP1", "CUSIP2"));
        SecurityRequest request = new SecurityRequest()
                .fetchAltInformation()
                .fetchAltIdentifications()
                .fetchDebt()
                .fetchPaymentInformation();

        when(vdmFrbSecurityService.checkExistingSecurities(internalSecurities))
                .thenCallRealMethod();
        when(vdmFrbService.fetchVdmFrbEntityList()).thenReturn(internalSecurities);
        when(securityCollection.find(request)).thenReturn(Arrays.asList(
                createSecurity("CUSIP1"),
                createSecurity("CUSIP2")
        ));

        // Act
        Set<Security> result = vdmFrbSecurityService.checkExistingSecurities(internalSecurities);

        // Assert
        assertEquals(2, result.size());
        verify(vdmFrbService).fetchVdmFrbEntityList();
        verify(securityCollection).find(request);
    }

    @Test
    void createOrUpdateVdmFrbSecurity_existingSecurity_shouldUpdateSecurity() {
        // Arrange
        VdmFrbEntity incoming = createVdmFrbEntity("CUSIP1");
        Set<Security> existingSecurity = new HashSet<>(Collections.singletonList(createSecurity("CUSIP1")));

        when(vdmFrbSecurityService.createOrUpdateVdmFrbSecurity(incoming, existingSecurity))
                .thenCallRealMethod();
        when(vdmFrbSecurityService.findSecurity(incoming, existingSecurity))
                .thenReturn(Optional.of(createSecurity("CUSIP1")));

        // Act
        Security result = vdmFrbSecurityService.createOrUpdateVdmFrbSecurity(incoming, existingSecurity);

        // Assert
        assertEquals("CUSIP1", result.getIdentifier());
        verify(logger).info("existing security");
        verify(vdmFrbSecurityService).findSecurity(incoming, existingSecurity);
    }

    @Test
    void createOrUpdateVdmFrbSecurity_newSecurity_shouldCreateSecurity() {
        // Arrange
        VdmFrbEntity incoming = createVdmFrbEntity("CUSIP2");
        Set<Security> existingSecurity = new HashSet<>(Collections.singletonList(createSecurity("CUSIP1")));

        when(vdmFrbSecurityService.createOrUpdateVdmFrbSecurity(incoming, existingSecurity))
                .thenCallRealMethod();
        when(vdmFrbSecurityService.findSecurity(incoming, existingSecurity))
                .thenReturn(Optional.empty());

        // Act
        Security result = vdmFrbSecurityService.createOrUpdateVdmFrbSecurity(incoming, existingSecurity);

        // Assert
        assertEquals("CUSIP2", result.getIdentifier());
        verify(logger).info("new security");
        verify(vdmFrbSecurityService).findSecurity(incoming, existingSecurity);
    }

    // Helper methods to create entities and securities for testing

    private VdmFrbEntity createVdmFrbEntity(String cusip) {
        VdmFrbEntity entity = new VdmFrbEntity();
        entity.setCusip(cusip);
        entity.setSecuritiesDesc("Description");
        // Set other properties as needed
        return entity;
    }

    private Security createSecurity(String identifier) {
        Security security = new Security();
        security.setIdentifier(identifier);
        // Set other properties as needed
        return security;
    }
}
