/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.user.consoleapp.presentation.booking;

import eapli.framework.actions.Action;

/**
 *
 * @author Meireles
 */
public class ViewBookingsForNextDaysAction implements Action {

    @Override
    public boolean execute() {
        return new ViewBookingsForNextDaysUI().show();
    }

}
