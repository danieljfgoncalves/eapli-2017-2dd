package eapli.ecafeteria.user.consoleapp.presentation.authz;

import eapli.ecafeteria.application.cafeteria.SignupController;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.util.Strings;
import eapli.util.io.Console;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class SignupRequestUI extends AbstractUI {

    private final SignupController theController = new SignupController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final UserDataWidget userData = new UserDataWidget();

        userData.show();

        final SelectWidget<OrganicUnit> selector = new SelectWidget<>("Organic units:",
                this.theController.organicUnits(), new OrganicUnitUIVisitor());
        selector.show();

        final OrganicUnit organicUnit = selector.selectedElement();

        if(organicUnit==null){
            return true;
        }

        System.out.println(theController.getMecanographicNumberInstructions(organicUnit));

        final String mecanographicNumber = Console.readLine("Mecanographic Number");

        try {
            this.theController.signup(userData.username(), userData.password(), userData.firstName(),
                    userData.lastName(), userData.email(), organicUnit, mecanographicNumber);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            Logger.getLogger(SignupRequestUI.class.getName()).log(Level.SEVERE, null, e);
            printErrorMessage("Ups! We were unable to save your data.");
        } catch (final IllegalStateException e) {
            printErrorMessage(e.getMessage());
        }

        return false;
    }

    private void printErrorMessage(String message) {
        if (Strings.isNullOrEmpty(message)) {
            message = "Ups! Something went wrong.";
        }
        System.out.println(message);
        System.out.println("The sign up was unsuccessful. Please, try again.");
    }

    @Override
    public String headline() {
        return "Sign Up";
    }
}
