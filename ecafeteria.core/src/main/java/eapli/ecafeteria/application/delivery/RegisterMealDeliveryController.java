package eapli.ecafeteria.application.delivery;

import eapli.ecafeteria.application.booking.ListBookingsService;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteria.MecanographicNumber;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.LinkedList;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class RegisterMealDeliveryController implements Controller {

    private final BookingRepository bookingRepo = PersistenceContext.repositories().bookings();

    private final CafeteriaUserRepository userRepo = PersistenceContext.repositories().cafeteriaUsers(null);
    private final CafeteriaUser user;

    public RegisterMealDeliveryController(String mecanographicNumberString) {
        MecanographicNumber mecanographicNumber = new MecanographicNumber(mecanographicNumberString);
        user = userRepo.findByMecanographicNumber(mecanographicNumber);
        if (user == null) {
            throw new IllegalStateException();
        }
    }

    /**
     * Registers the last meal that this user booked.
     *
     * @return true if meal delivery was registered
     */
    public boolean registerMealDelivery() {

        ListBookingsService bookingsService = new ListBookingsService();
        LinkedList<Booking> bookingList = new LinkedList<>();
        bookingsService.findBookingsStateDefinitiveOf(user).iterator().forEachRemaining(bookingList::add);
        if (!bookingList.isEmpty()) {
            Booking tempBooking = bookingList.getLast();
            try {
            
                tempBooking.deliver();
                bookingRepo.save(tempBooking);
            } catch (IllegalStateException |DataConcurrencyException | DataIntegrityViolationException ex) {
                return false;
            }

            return true;
        }
        return false;
    }
    
    

    /**
     * Registers a specific meal of this user
     *
     * @param meal the meal to deliver
     * @return true if the meal delivery was registered
     */
    public boolean registerMealDelivery(Meal meal) {
        Booking tempBooking = bookingRepo.findBookingByUserAndMealAndState(user, meal, BookingState.DEFINITIVE);
        tempBooking.deliver();
        return true;
    }

}
