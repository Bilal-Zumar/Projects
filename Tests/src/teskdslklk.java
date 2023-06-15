import com.dtcc.smo.apisdk.SecurityCollection;
import com.dtcc.smo.apisdk.exception.ClientError;
import com.dtcc.smo.apisdk.exception.SmoApiException;
import com.dtcc.smo.apisdk.model.*;
import com.dtcc.smo.apisdk.request.SecurityPersistRequest;
import com.dtcc.smo.apisdk.request.SecurityRequest;
import com.dtcc.smo.entities.VdmFrbEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VdmFrbSecurityService {

    final Logger logger = LoggerFactory.getLogger(VdmFrbSecurityService.class);


    private static final Long VENDORINTERNALID = 3L;
    private static final String VENDORCOUNTRY = "USA";
    private static final HashMap<String, String> vendorDescList = new HashMap<>();

    static {
        vendorDescList.put("FNMA", "Federal National Mortgage Association (Fannie Mae)");
        vendorDescList.put("GNMA", "Government National Mortgage Association - Ginnie Mae");
        vendorDescList.put("FHLMC", "Federal Home Loan Mortgage Corporation (Freddie Mac)");
    }

    private final VdmFrbService vdmFrbService;

    private final SecurityCollection securityCollection;

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public VdmFrbSecurityService(final SecurityCollection securityCollection,
                                 final VdmFrbService vdmFrbService) {
        this.securityCollection = securityCollection;
        this.vdmFrbService = vdmFrbService;
        mapper.registerModule(new JavaTimeModule());
    }

    public void process() {

        List<VdmFrbEntity> vdmFrbEntities = vdmFrbService.fetchVdmFrbEntityList();
        Set<Security> existingSecurities = checkExistingSecurities(vdmFrbEntities);
        final List<Security> toPersist = vdmFrbEntities.stream()
                .map(entity -> {
                    logger.debug("Processing security identified by: {}", entity);
                    return createOrUpdateVdmFrbSecurity(entity, existingSecurities);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        persistSecuritiestoSMO(toPersist);
    }

    private Optional<Security> findSecurity(VdmFrbEntity incoming, Set<Security> existingSecurities) {
        return existingSecurities.stream()
                .filter(security ->
                        incoming.getCusip().equalsIgnoreCase(security.getIdentifier())
                )
                .findFirst();
    }

    private void persistSecuritiestoSMO(List<Security> toPersist) {
        if (toPersist.isEmpty()) {
            logger.info("No Securities to persist");
        } else {
            logger.info("Persisting {} securities", toPersist.size());
            final SecurityPersistRequest persistRequest = new SecurityPersistRequest(toPersist);
            try {
                securityCollection.persist(persistRequest);
            } catch (final ClientError exception) {
                logger.error("Unable to persist securities at the SMO-API due to validation violations", exception);
                exception.getErrors().forEach(error -> {
                            logger.error(
                                    "Violation: \"{}\" at \"{}\" and cusip \"{}\" ",
                                    error.getMessage(),
                                    error.getPath(), toPersist.get(Integer.parseInt(error.getPath().substring(error.getPath().indexOf("[") + 1, error.getPath().indexOf("]")))).getIdentifier());
                        }
                );
                throw new RuntimeException("Unable to persist securities at the SMO-API due to validation violations");
            } catch (final SmoApiException exception) {
                logger.error("Unable to persist securities at the SMO-API", exception);
                throw new RuntimeException("Unable to persist securities at the SMO-API");
            }
        }
    }


    public Set<Security> checkExistingSecurities(final List<VdmFrbEntity> internalSecurities) {
        final Set<String> identifier = internalSecurities.stream()
                .filter(Objects::nonNull)
                .map(VdmFrbEntity::getCusip)
                .collect(Collectors.toSet());
        if (identifier.isEmpty()) {
            return new HashSet<>();
        }
        final SecurityRequest request = new SecurityRequest()
                .fetchAltInformation()
                .fetchAltIdentifications()
                .fetchDebt()
                .fetchPaymentInformation();

        request.setIdentifiers(identifier);
        try {
            return securityCollection.find(request)
                    .stream()
                    .collect(Collectors.toSet());
        } catch (final SmoApiException exception) {

            logger.error("Unable to fetch securities from the SMO-API", exception);
            throw new RuntimeException("Unable to fetch securities from the SMO-API", exception);
        }
    }

    public Security createOrUpdateVdmFrbSecurity(VdmFrbEntity incoming, Set<Security> existingSecurity) {
        Optional<Security> optionalSecurity = findSecurity(incoming, existingSecurity);
        if (optionalSecurity.isPresent()) {
            logger.info("existing security");
            Security security = optionalSecurity.get();
            mapSecurityValues(incoming, security);
            return security;
        } else {
            logger.info("new security");
            return createNewEmbsSecurity(incoming);
        }
    }

    public void mapSecurityValues(VdmFrbEntity incoming, Security security) {
        SecurityAltInformation securityAltInformation = new SecurityAltInformation();
        securityAltInformation.setShortDescription(incoming.getSecuritiesDesc());

        //SecurityAltIdentification securityAltIdentification = new SecurityAltIdentification(incoming.getPoolNum(),incoming.getEligForBrdcstFrom());
        //securityAltIdentification.setAlternateId(incoming.getPoolNum());
        // security.getAltIdentifications().add(securityAltIdentification);

        Debt debt = new Debt();
        debt.setBondDenominationAmount(incoming.getMinParAmt());
        //debt.setMinimumDenominationAmount(incoming.getMinimumDenominationAmount());
        SecurityPaymentInformation securityPaymentInformation = new SecurityPaymentInformation();
        securityPaymentInformation.setInterestRate(incoming.getIntRate());
        //securityPaymentInformation.setInterestPaymentFrequencyCode(incoming.getIntFreq());
        //securityPaymentInformation.setAmortizationTypeCode(incoming.getAmortizedFlag());
        //security.setIdentifier(incoming.getCusip();
        security.setAltInformation(securityAltInformation);
        //security.setCertificateTypeCode(incoming.getEligForBrdcstFrom());
        //security.setAltIdentifications(Collections.singleton(securityAltIdentification));
        //security.setIssueDate(incoming.getIssueDate());
        //security.setPaymentInformation(securityPaymentInformation);
        //security.setMaturityDate(incoming.getMaturityDate());
        security.setDebt(debt);
    }

    public Security createNewEmbsSecurity(VdmFrbEntity incoming) {
        SecurityAltInformation securityAltInformation = new SecurityAltInformation();
        securityAltInformation.setShortDescription(incoming.getSecuritiesDesc());

        SecurityAltIdentification securityAltIdentification = new SecurityAltIdentification();
        securityAltIdentification.setAlternateId(incoming.getPoolNum());

        SecurityPaymentInformation securityPaymentInformation = new SecurityPaymentInformation();
        securityPaymentInformation.setInterestRate(incoming.getIntRate());
        securityPaymentInformation.setInterestPaymentFrequencyCode(incoming.getIntFreq());
        securityPaymentInformation.setAmortizationTypeCode(incoming.getAmortizedFlag());
        Debt debt = new Debt();
        debt.setBondDenominationAmount(incoming.getMinParAmt());
        //debt.setMinimumDenominationAmount(incoming.getMinimumDenominationAmount());

        Security security = new Security();
        security.setIdentifier(incoming.getCusip());
        security.setCertificateTypeCode(incoming.getEligForBrdcstFrom());
        security.setIssueDate(incoming.getIssueDate());
        security.setMaturityDate(incoming.getMaturityDate());
        security.setDebt(debt);
        security.setPaymentInformation(securityPaymentInformation);
        security.setAltIdentifications(Collections.singleton(securityAltIdentification));
        security.setAltInformation(securityAltInformation);

        return security;
    }
}
