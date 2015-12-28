import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;
/**
 * The court class manages the court and has a court number and a list of bookings for that court
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Court implements Serializable
{
    // instance variables - replace the example below with your own
    private int courtNumber;
    private List<Booking> courtBookings;

    /**
     * Constructor for objects of class Court
     * 
     * @param courtNumber. The court number is int
     */
    public Court(int courtNumber)
    {
        // initialise instance variables
        this.courtNumber = courtNumber;
        courtBookings = new ArrayList<Booking>();
    }
    
    /*public boolean addCourtBooking(Booking booking){
       boolean result=false; 
        for(Booking b:courtBookings)
        {
            if(booking.clash(b))
            {
            }else{
        courtBookings.add(booking);}
    }
    }*/
    
    /**
     * This method add booking in the booking list of the court
     * 
     * @param Booking. The booking object
     * @return boolean. Return true if booking is added to the list and false if booking is not
     * added to the list
     */
    public boolean addCourtBooking(Booking booking){
        if(courtBookings.add(booking))
            return true;
        return false;
    }
    
    /**
     * This method retrieves the booking list of the court
     * 
     * @return Booking list. The booking list booking object
     */
    public List<Booking> getCourtBooking(){
        return courtBookings;
    }
    
    /**
     * This method set the courtNumber
     * 
     * @param courtNumber. The court number is int
     */
    public void setCourtNumber(int courtNumber){
        this.courtNumber = courtNumber;
    }
    
    /**
     * This method retrieves the court number
     * 
     * @return court number. The court number is int
     */
    public int getCourtNumber(){
        return courtNumber;
    }
    
    public boolean deleteBooking(Booking booking){
        if(courtBookings.remove(booking))
            return true;
        return false;
    }
    
    public boolean checkClash(Booking proposedBooking){
        boolean result = true;
        if(courtBookings.isEmpty())
            result = true;
        else{
            for(Booking b : courtBookings){
                result = proposedBooking.checkClash(b);
                if(result == false)
                    break;
            }
        }
        return result;
    }
}
