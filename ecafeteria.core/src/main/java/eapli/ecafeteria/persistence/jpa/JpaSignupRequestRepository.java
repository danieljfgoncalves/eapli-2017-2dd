package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.mealbooking.SignupRequest;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 * 02/04/2016
 */
class JpaSignupRequestRepository extends JpaRepository<SignupRequest, Username> implements SignupRequestRepository {

    @Override
    protected String persistenceUnitName() {
        return PersistenceSettings.PERSISTENCE_UNIT_NAME;
    }

    @Override
    public Iterable<SignupRequest> listSignupRequestsPending() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      //FIXME filter just pending SignupRequests
      return this.all();
    }

}
