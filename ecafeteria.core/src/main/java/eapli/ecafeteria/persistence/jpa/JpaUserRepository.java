package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.users.SystemUser;
import eapli.ecafeteria.domain.users.Username;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaRepository;

/**
 *
 * Created by nuno on 20/03/16.
 */
class JpaUserRepository extends JpaRepository<SystemUser, Username> implements UserRepository {

    @Override
    protected String persistenceUnitName() {
        return PersistenceSettings.PERSISTENCE_UNIT_NAME;
    }

}
