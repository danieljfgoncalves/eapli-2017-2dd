package eapli.ecafeteria.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.domain.AggregateRoot;
import eapli.util.Strings;

/**
 * An Cafeteria User.
 *
 * This class represents cafeteria users. It follows a DDD approach where User
 * is the root entity of the Cafeteria User Aggregate and all of its properties
 * are instances of value objects. An Cafeteria User contains an User in it
 *
 * This approach may seem a little more complex than just having String or
 * native type attributes but provides for real semantic of the domain and
 * follows the Single Responsibility Pattern
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 *
 */
@Entity
public class CafeteriaUser implements AggregateRoot<MecanographicNumber>, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @OneToOne(cascade = CascadeType.PERSIST)
    private SystemUser systemUser;
    private Account account;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private OrganicUnit organicUnit;

    @Id
    private MecanographicNumber mecanographicNumber;
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    // TODO check if the string account parameter is really needed
    public CafeteriaUser(SystemUser user, String account, OrganicUnit organicUnit, String mecanographicNumber) {
        if (mecanographicNumber == null || user == null || organicUnit == null
                || Strings.isNullOrEmpty(mecanographicNumber)) {
            throw new IllegalStateException();
        }
        this.systemUser = user;
        this.account = new Account(account);
        this.organicUnit = organicUnit;
        this.mecanographicNumber = new MecanographicNumber(mecanographicNumber);
        // by default
        this.status = Status.APPROVAL_PENDING;
    }

    protected CafeteriaUser() {
        // for ORM only
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CafeteriaUser)) {
            return false;
        }

        final CafeteriaUser other = (CafeteriaUser) o;
        if (!this.mecanographicNumber.equals(other.mecanographicNumber)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return this.mecanographicNumber.hashCode();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof CafeteriaUser)) {
            return false;
        }

        final CafeteriaUser that = (CafeteriaUser) other;
        if (this == that) {
            return true;
        }
        if (!this.mecanographicNumber.equals(that.mecanographicNumber)) {
            return false;
        }

        if (!this.systemUser.equals(that.systemUser)) {
            return false;
        }

        if (!this.account.equals(that.account)) {
            return false;
        }
        if (!this.organicUnit.equals(that.organicUnit)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean is(MecanographicNumber id) {
        return id().equals(id);
    }

    public MecanographicNumber mecanographicNumber() {
        return this.mecanographicNumber;
    }

    @Override
    public MecanographicNumber id() {
        return this.mecanographicNumber;
    }

    public Account account() {
        return this.account;
    }

    public OrganicUnit organicUnit() {
        return this.organicUnit;
    }
}
