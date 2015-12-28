import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
/**
 * This Sport Class support two type of game. tennis and squash which can be pass in 
 * constructor. It has courts, usage fee, insurance fee and affiliation fee.
 * 
 * @author (Samim Ahmed) 
 * @version (27.4.2015)
 */
public abstract class Sport implements Serializable
{
    // instance variables - replace the example below with your own
    private String name;
    private int duration;
    private List<Court> courts;
    private double usageFees;
    private double insuranceFees;
    private double affiliationFees;
    
    /**
     * Constructor for objects of class Sport
     * 
     * @param String. The string is a name of the sport.
     */
    public Sport(String name)
    {
        // initialise instance variables
        this.name = name;
        courts = new ArrayList<Court>();
        usageFees = 0.0;
        insuranceFees = 0.0;
        affiliationFees = 0.0;
    }
    
    /**
     * This method add court to the sport
     * 
     * @param Court. The court is object.
     * @return boolean. Returns true if court is added and false if court is not added
     */
    public boolean addCourt(Court court){
        if(this.courts.add(court))
            return true;
        return false;
    }
    
    public List<Court> getCourt(){
        return courts;
    }
    /**
     * This method retrieves the court object of particular court number
     * 
     * @param int . The courtNumber is int
     * @return Court. The court is a object
     */
    public Court getCourt(int courtNumber){
        for(Court element : courts){
            if(element.getCourtNumber() == courtNumber)
                return element;
        }        
        return null;   
    }
    
    /**
     * This method sets the name of the sport
     * 
     * @param name. This name is a String
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * This method retrieves the name of the sport
     * 
     * @return String . The string is the name of the sport
     */
    public String getName(){
        return name;
    }
    
    /**
     * This method set usageFees of the sport
     * 
     * @param double. The usageFees is the double
     */
    public void setUsageFees(double usageFees){
        this.usageFees = usageFees;
    }
    
    /**
     * This method retrieves the usageFee of the sport
     * 
     * @return double. This is usageFees in double
     */
    public double getUsageFees(){
        return usageFees;
    }
    
    /**
     * This method set insuranceFees of the sport
     * 
     * @param double. The insuranceFees is the double
     */
    public void setInsuranceFees(double insuranceFees){
        this.insuranceFees = insuranceFees;
    }
    
    /**
     * This method retrieves the insuranceFees of the sport
     * 
     * @return double. This is insuranceFees in double
     */
    public double getInsuranceFees(){
        return insuranceFees;
    }
    
    /**
     * This method set affiliationFees of the sport
     * 
     * @param double. The affiliationFees is the double
     */
    public void setAffiliationFees(double affiliationFees){
        this.affiliationFees = affiliationFees;
    }
    
    /**
     * This method retrieves the affiliationFee of the sport
     * 
     * @return double. This is affiliationFee in double
     */
    public double getAffiliationFees(){
        return affiliationFees;
    }
    
    
    public List<Court> getAvailableCourt(Booking pBooking){
        List<Court> availableCourt = new ArrayList<Court>();
        List<Booking> listOfCourtBooking = new ArrayList<Booking>();
        for(Court courtElement : getCourt()){
            boolean courtAllowed = true;
            int i = 1;
            
            listOfCourtBooking = courtElement.getCourtBooking();
            if(listOfCourtBooking.isEmpty()){
                availableCourt.add(courtElement);
            }else{
                for(Booking bookingElement : listOfCourtBooking){
                    courtAllowed = courtElement.checkClash(pBooking);
                    
                    if((i++ == courtElement.getCourtBooking().size()) && (courtAllowed == true)){
                        System.out.println("code reached here2");
                        availableCourt.add(courtElement);
                    }
                    if(courtAllowed == false)
                        break;
                }
            }
        }
        return availableCourt;
    }
}
