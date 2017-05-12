/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Miguel Silva - 1150901
 */
public class InMemoryBookingRepository extends InMemoryRepositoryWithLongPK<Booking>
        implements BookingRepository {

    @Override
    public Iterable<Booking> findBookingByUserAndState(CafeteriaUser user, BookingState state) {
        return match(e -> e.belongsTo(user) && e.isAtState(state));
    }

    /**
     * It provides all bookings, whose meal happens within a specified number of days, that are at one of the specified states and belongs to the specified user.
     * 
     * @param user The user to whom the Bookings belong.
     * @param states The states at which the bookings should be.
     * @param days The number of days (starting from the current day) in which the booking's meal should occur.
     * @return It returns all matching bookings.
     */
    @Override
    public Iterable<Booking> findBookingByUserAndStatesAndWithinDays(CafeteriaUser user, Iterable<BookingState> states, int days) {
        Calendar limitDate = Calendar.getInstance();
        limitDate.add(Calendar.DAY_OF_MONTH, days);
        return match(e -> e.belongsTo(user) && isBookingAtOneOfTheStates(states, e) && e.isUntilDate(limitDate));
    }

    @Override
    public Booking findBookingByUserAndMealAndState(CafeteriaUser user, Meal meal, BookingState state) {
        return matchOne(e -> e.belongsTo(user) && e.isOfMeal(meal) && e.isAtState(state));
    }

    @Override
    public Iterable<Booking> checkBookingsByDateMealAndDishType(Date date, MealType mealType, DishType dishType) {
        //TODO: Implement the method
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Booking> allNonEvaluatedBy(CafeteriaUser user) {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * It checks if the booking is at one of the states of the list.
     *
     * @param states The list with the states.
     * @param booking THe booking to be examined.
     * @return It returns "true" if the booking is at one of the states within
     * the list or "false" otherwise.
     */
    private boolean isBookingAtOneOfTheStates(Iterable<BookingState> states, Booking booking) {
        for (BookingState state : states) {
            if (booking.isAtState(state)) {
                return true;
            }
        }
        return false;
    }

}
