import com.dtcc.smo.apisdk.Security;
import com.dtcc.smo.apisdk.SecurityCollection;
import com.dtcc.smo.apisdk.exception.ClientError;
import com.dtcc.smo.apisdk.exception.SmoApiException;
import com.dtcc.smo.apisdk.model.*;
import com.dtcc.smo.apisdk.request.SecurityPersistRequest;
import com.dtcc.smo.apisdk.request.SecurityRequest;
import com.dtcc.smo.entities.VdmFrbEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VdmFrbSecurityServiceTest {

    @Mock
    private Logger logger;

    @Mock
    private SecurityCollection securityCollection;

    @Mock
    private VdmFrbService vdmFrbService;

    @InjectMocks
    private VdmFrbSecurityService vdmFrbSecurityService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void process_noSecuritiesToPersist_shouldLogInfoMessage() throws SmoApiException, ClientError {
        // Arrange
        List<VdmFrbEntity> vdmFrbEntities = Collections.emptyList();
        Set<Security> existingSecurities = new HashSet<>();
        SecurityPersistRequest persistRequest = new SecurityPersistRequest(Collections.emptyList());

        when(vdmFrbService.fetchVdmFrbEntityList()).thenReturn(vdmFrbEntities);
        when(vdmFrbSecurityService.checkExistingSecurities(vdmFrbEntities)).thenReturn(existingSecurities);

        // Act
        vdmFrbSecurityService.process();

        // Assert
        verify(vdmFrbService).fetchVdmFrbEntityList();
        verify(vdmFrbSecurityService).checkExistingSecurities(vdmFrbEntities);
        verify(securityCollection, never()).persist(any(SecurityPersistRequest.class));
        verify(logger).info("No Securities to persist");
    }

    @Test
    void process_securitiesToPersist_shouldPersistSecurities() throws SmoApiException, ClientError {
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
        verify(securityCollection).persist(persistRequest);
        verify(logger).info("Persisting 2 securities");
    }

    @Test
    void checkExistingSecurities_noInternalSecurities_shouldReturnEmptySet() throws SmoApiException {
        // Arrange
        List<VdmFrbEntity> internalSecurities = Collections.emptyList();

        when(vdmFrbService.fetchVdmFrbEntityList()).thenReturn(internalSecurities);
        when(securityCollection.find(any(SecurityRequest.class))).thenReturn(Collections.emptyList());

        // Act
        Set<Security> result = vdmFrbSecurityService.checkExistingSecurities(internalSecurities);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(vdmFrbService).fetchVdmFrbEntityList();
        verify(securityCollection, never()).find(any(SecurityRequest.class));
    }

    @Test
    void checkExistingSecurities_internalSecuritiesExist_shouldReturnExistingSecurities() throws SmoApiException {
        // Arrange
        List<VdmFrbEntity> internalSecurities = Arrays.asList(
                createVdmFrbEntity("CUSIP1"),
                createVdmFrbEntity("CUSIP2")
        );
        SecurityRequest request = new SecurityRequest().fetchAltInformation().fetchAltIdentifications()
                .fetchDebt().fetchPaymentInformation();

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
    void findSecurity_existingSecurity_shouldReturnSecurity() {
        // Arrange
        VdmFrbEntity incoming = createVdmFrbEntity("CUSIP1");
        Set<Security> existingSecurity = new HashSet<>(Collections.singletonList(createSecurity("CUSIP1")));

        when(vdmFrbSecurityService.findSecurity(incoming, existingSecurity)).thenCallRealMethod();

        // Act
        Optional<Security> result = vdmFrbSecurityService.findSecurity(incoming, existingSecurity);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("CUSIP1", result.get().getIdentifier());
    }

    @Test
    void findSecurity_nonExistingSecurity_shouldReturnEmptyOptional() {
        // Arrange
        VdmFrbEntity incoming = createVdmFrbEntity("CUSIP2");
        Set<Security> existingSecurity = new HashSet<>(Collections.singletonList(createSecurity("CUSIP1")));

        when(vdmFrbSecurityService.findSecurity(incoming, existingSecurity)).thenCallRealMethod();

        // Act
        Optional<Security> result = vdmFrbSecurityService.findSecurity(incoming, existingSecurity);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void persistSecuritiestoSMO_noSecuritiesToPersist_shouldLogInfoMessage() throws SmoApiException, ClientError {
        // Arrange
        List<Security> toPersist = Collections.emptyList();
        SecurityPersistRequest persistRequest = new SecurityPersistRequest(toPersist);

        // Act
        vdmFrbSecurityService.persistSecuritiestoSMO(toPersist);

        // Assert
        verify(securityCollection, never()).persist(any(SecurityPersistRequest.class));
        verify(logger).info("No Securities to persist");
    }

    @Test
    void persistSecuritiestoSMO_securitiesToPersist_successfullyPersistsSecurities() throws SmoApiException, ClientError {
        // Arrange
        List<Security> toPersist = Arrays.asList(
                createSecurity("CUSIP1"),
                createSecurity("CUSIP2")
        );
        SecurityPersistRequest persistRequest = new SecurityPersistRequest(toPersist);

        when(securityCollection.persist(persistRequest)).thenReturn(Collections.emptyList());

        // Act
        vdmFrbSecurityService.persistSecuritiestoSMO(toPersist);

        // Assert
        verify(securityCollection).persist(persistRequest);
        verify(logger, never()).error(anyString(), any(Throwable.class));
    }

    @Test
    void persistSecuritiestoSMO_validationViolations_shouldLogErrorsAndThrowException() throws SmoApiException, ClientError {
        // Arrange
        List<Security> toPersist = Arrays.asList(
                createSecurity("CUSIP1"),
                createSecurity("CUSIP2")
        );
        SecurityPersistRequest persistRequest = new SecurityPersistRequest(toPersist);
        List<ClientError.Error> errors = Arrays.asList(
                new ClientError.Error("Error 1", "path[0]"),
                new ClientError.Error("Error 2", "path[1]")
        );
        ClientError clientError = new ClientError("Validation violations", errors);

        when(securityCollection.persist(persistRequest)).thenThrow(clientError);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> vdmFrbSecurityService.persistSecuritiestoSMO(toPersist));
        verify(securityCollection).persist(persistRequest);
        verify(logger).error(eq("Unable to persist securities at the SMO-API due to validation violations"), any(ClientError.class));
        verify(logger).error(eq("Violation: \"Error 1\" at \"path[0]\" and cusip \"CUSIP1\""));
        verify(logger).error(eq("Violation: \"Error 2\" at \"path[1]\" and cusip \"CUSIP2\""));
    }

    @Test
    void persistSecuritiestoSMO_smoApiException_shouldLogErrorAndThrowException() throws SmoApiException, ClientError {
        // Arrange
        List<Security> toPersist = Arrays.asList(
                createSecurity("CUSIP1"),
                createSecurity("CUSIP2")
        );
        SecurityPersistRequest persistRequest = new SecurityPersistRequest(toPersist);
        SmoApiException smoApiException = new SmoApiException("API Exception");

        when(securityCollection.persist(persistRequest)).thenThrow(smoApiException);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> vdmFrbSecurityService.persistSecuritiestoSMO(toPersist));
        verify(securityCollection).persist(persistRequest);
        verify(logger).error(eq("Unable to persist securities at the SMO-API"), any(SmoApiException.class));
    }

    @Test
    void createOrUpdateVdmFrbSecurity_existingSecurity_shouldUpdateSecurity() {
        // Arrange
        VdmFrbEntity incoming = createVdmFrbEntity("CUSIP1");
        Set<Security> existingSecurity = new HashSet<>(Collections.singletonList(createSecurity("CUSIP1")));
        Security expectedSecurity = createSecurity("CUSIP1");

        when(vdmFrbSecurityService.findSecurity(incoming, existingSecurity)).thenReturn(Optional.of(expectedSecurity));

        // Act
        Security result = vdmFrbSecurityService.createOrUpdateVdmFrbSecurity(incoming, existingSecurity);

        // Assert
        assertEquals(expectedSecurity, result);
    }

    @Test
    void createOrUpdateVdmFrbSecurity_nonExistingSecurity_shouldCreateSecurity() {
        // Arrange
        VdmFrbEntity incoming = createVdmFrbEntity("CUSIP2");
        Set<Security> existingSecurity = new HashSet<>(Collections.singletonList(createSecurity("CUSIP1")));
        Security expectedSecurity = createSecurity("CUSIP2");

        when(vdmFrbSecurityService.findSecurity(incoming, existingSecurity)).thenReturn(Optional.empty());
        when(securityCollection.create(incoming)).thenReturn(expectedSecurity);

        // Act
        Security result = vdmFrbSecurityService.createOrUpdateVdmFrbSecurity(incoming, existingSecurity);

        // Assert
        assertEquals(expectedSecurity, result);
        verify(securityCollection).create(incoming);
    }

    private VdmFrbEntity createVdmFrbEntity(String cusip) {
        VdmFrbEntity entity = new VdmFrbEntity();
        entity.setCusip(cusip);
        return entity;
    }

    private Security createSecurity(String identifier) {
        Security security = new Security();
        security.setIdentifier(identifier);
        return security;
    }
}
