import java.util.Date;
import java.io.Serializable;
/**
 * This is booking class with start date, end date and duration of the game and the booking is also associated with Member and Court.
 * 
 * @author (Samim Ahmed) 
 * @version (27.4.2015)
 */
public class Booking implements Comparable<Booking>, Serializable
{
    // instance variables - replace the example below with your own
    private Member bookedBy;
    private Court court;
      
    private Date startDate;
    private Date endDate;
    private int duration;
    
    /**
     * Constructor for objects of class Booking
     */
    public Booking()
    {
        // initialise instance variables
        
    }
    
    /**
     * This method sets the duration of the game
     * 
     * @param duration. This duration of the game is int which is minute
     */
    public void setDuration(int duration){
        this.duration = duration;
    }
    
    /**
     * This method retrieves duration of the game in minute
     * 
     * @return duration. It returns int which is minute
     */
    public int getDuration(){
        return duration;
    }
    
    /**
     * This method sets start date and start time
     * 
     * @param Date. The date contains the start date and start time
     */
    public void setStartDate(Date date){
        this.startDate = date;
    }
    
    /**
     * This method retrieves the start date which includes start time
     * 
     * @return Date. The date contains the start date and start time
     */
    public Date getStartDate(){
        return startDate;
    }
    
    /**
     * This method sets end date and end time
     * 
     * @param Date. The date contains the end date and end time
     */
    public void setEndDate(Date date){
        this.endDate = date;
    }
    
    /**
     * This method retrieves the end date which includes start time
     * 
     * @return Date. The date contains the end date and end time
     */
    public Date getEndDate(){
        return endDate;
    }
    
    /**
     * This method set the booking for member
     * 
     * @param Member. The member object
     */
    public void setBookedBy(Member member){
        this.bookedBy = member;
    }
    
    /**
     * This method retrieves the Member object
     * 
     * @return Member. The member object
     */
    public Member getBookedBy(){
        return bookedBy;
    }
    
    /**
     * This method set the court for booking
     * 
     * @param Court. The court object
     */
    public void setCourt(Court court){
        this.court = court;
    }
    
    
    /**
     * This method get the court for booking
     * 
     * @param Court. The court object
     */
    public Court getCourt(){
        return court;
    }
    
    public boolean checkClash(Booking b){
        boolean result = false;
        Date startDate1 = b.getStartDate();
        Date endDate1 = b.getEndDate();
        Date startDate2 = startDate;
        Date endDate2 = endDate;
        if(startDate2.after(startDate1)){
            if(startDate2.after(endDate1)){
                result = true;
            }else
                result = false;
        }else if(startDate2.before(startDate1)){
            if(endDate2.before(startDate1))
                result = true;
            else
                result = false;
        }
        return result;

    }
    
    @Override
    public int compareTo(Booking other) {
        return getStartDate().compareTo(other.getStartDate());
    }
}
