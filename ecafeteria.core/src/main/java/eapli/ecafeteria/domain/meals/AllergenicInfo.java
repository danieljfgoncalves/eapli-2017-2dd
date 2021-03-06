package eapli.ecafeteria.domain.meals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * This is the allergenic information of a dish, it has the allergens contained
 * in the respective dish
 *
 * @FIXME is this an entity, a value object or an aggregate?
 *
 *
 * Created by k4rd050 on 04-05-2017.
 */
@Entity
public class AllergenicInfo implements Serializable {

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long pk;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Allergen> allergens = new ArrayList<>();

    public AllergenicInfo(List<Allergen> allergens) {
        if (allergens == null) {
            throw new IllegalStateException();
        }
        this.allergens = new ArrayList<>(allergens);
    }

    public List<Allergen> allergens() {
        return new ArrayList<>(allergens);
    }

    protected AllergenicInfo() {
        // for ORM only
    }
}
