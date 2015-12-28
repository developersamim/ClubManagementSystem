import java.util.List;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.Serializable;
/**
 * The member has a member number, member name, member financial
 * The member class has list of sports played by member and a list of booking booked by member
 * 
 * @author (Samim Ahmed) 
 * @version (27.4.2015)
 */
public class Member implements Serializable
{
    // instance variables - replace the example below with your own
    private List<Sport> memberSports;
    private List<Booking> memberBookings;
    
    private String memberNumber;
    private String memberName;
    private boolean memberFinancial;

    /**
     * Constructor for objects of class Member
     * This constructor doesnot take any parameter
     */
    public Member()
    {
        // initialise instance variables
        memberSports = new ArrayList<Sport>();
        memberBookings = new ArrayList<Booking>();
        //Collections.sort(memberBookings);
    }
    
    /**
     * Constructor for objects of class Member
     * This constructor takes three parameters member number, member name, member financial
     */
    public Member(String memberNumber, String memberName, boolean memberFinancial){
        memberSports = new ArrayList<Sport>();
        memberBookings = new ArrayList<Booking>();
        this.memberNumber = memberNumber;
        this.memberName = memberName;
        this.memberFinancial = memberFinancial;
    }
    
    /**
     * This method add sport to the sport list of member.
     * 
     * @param sport .Sport object
     * @return boolean .Return ture if sport is added and false if sport is not added
     */
    public boolean addMemberSports(Sport sport){
        if(memberSports.add(sport))
            return true;
        return false;
    }
    
    /**
     * This method is to retrieve all the sport played my member
     * 
     * @return Sport . It returns the list of sport object
     */
    public List<Sport> getMemberSports(){
        return memberSports;
    }
    
    /**
     * This method add booking to the member booking list
     * 
     * @param Booking . The Booking object
     * @return boolean. Return true if booking is added to the list and false if booking 
     * is not added
     */
    public boolean addMemberBooking(Booking booking){
        if(this.memberBookings.add(booking))
            return true;
        return false;
    }
    
    
    /**
     * This method retrieve the list of booking of member
     * 
     * @return booking list. List of booking object
     */
    public List<Booking> getMemberBooking(){
        return memberBookings;
    }
    
    /**
     * This method the member number of member
     * 
     * @param memberNumber. The member number is a String
     */
    public void setMemberNumber(String memberNumber){
        this.memberNumber = memberNumber;
    }
    
    /**
     * This method retrieve the member Number
     * 
     * @return memberNumber. The member number is a String
     */
    public String getMemberNumber(){
        return memberNumber;
    }
    
    /**
     * This method set the member name of the member
     * 
     * @param memberName. The member name is a String
     */
    public void setMemberName(String memberName){
        this.memberName = memberName;
    }
    
    /**
     * This method retrieves the member name
     * 
     * @return memberName. The member name is a String
     */
    public String getMemberName(){
        return memberName;
    }
    
    /**
     * This method set member financial true or false
     * 
     * @param memberFinancial. The member financial is boolean
     */
    public void setMemberFinancial(Boolean memberFinancial){
        this.memberFinancial = memberFinancial;
    }
    
    /**
     * This method retrieves the member financial
     * 
     * @return boolean. It returns true or false.
     */
    public Boolean getMemberFinancial(){
        return memberFinancial;
    }
    
    /**
     * This method checks whether specific sport is played by member
     * 
     * @param sport. The sport object and sported supported by club.
     * @return boolean. It returns true if sport is played by member and false
     * if sport is not played by member
     */
    public boolean checkSportPlayed(Sport sport){
        for(Sport element : getMemberSports()){
            if(element.getName().equals(sport.getName()))
                return true;
        }
        return false;
    }
    
    /**
     * This method deletes booking from booking list
     * 
     * @param Booking. This booking object
     * @return boolean. It returns true if it deletes the booking and false if it doesnt
     * delete the booking
     */
    public boolean deleteBooking(Booking booking){
        if(memberBookings.remove(booking))
            return true;
        return false;
    }
   
}
