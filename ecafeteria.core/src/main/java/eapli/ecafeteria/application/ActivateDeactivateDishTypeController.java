/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application;

import static eapli.ecafeteria.AppSettings.ensurePermissionOfLoggedInUser;

import eapli.ecafeteria.domain.DishType;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author mcn
 */
public class ActivateDeactivateDishTypeController implements Controller {

    public Iterable<DishType> listDishTypes() {
        // FIXME a controller should not call other controllers...
        return new ListDishTypeController().listDishTypes();
    }

    public void changeDishTypeState(DishType dType) {
        ensurePermissionOfLoggedInUser(ActionRight.ManageMenus);

        dType.changeDishTypeState();
        final DishTypeRepository dishTypeRepository = PersistenceContext.repositories().dishTypes();
        dishTypeRepository.save(dType);
    }

}
