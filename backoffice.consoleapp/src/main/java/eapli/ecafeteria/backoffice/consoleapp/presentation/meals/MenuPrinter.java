/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.*;
import eapli.framework.visitor.Visitor;


/**
 *
 * @author Eduangelo Ferreira
 */
class MenuPrinter implements Visitor<Menu> {

    @Override
    public void visit(Menu visitee) {

        Iterable<Meal> meals = visitee.getMeals();
        for (Meal me : meals) {
            System.out.printf("%-30s%-25s%-10s%20s%n", me.dish().name(), me.mealType().mealType(),
                    String.valueOf(visitee.isPublished()),visitee.organicUnit().description());
        }
    }

    @Override
    public void beforeVisiting(Menu visitee) {
        // nothing to do
    }

    @Override
    public void afterVisiting(Menu visitee) {
        // nothing to do
    }

}
