import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.Calendar;
/**
 * The Club has a list of members and sports. The club can add members, get member, add sport
 * to the club, can get sport from club.
 * 
 * @author (Samim Ahmed) 
 * @version (27.4.2015)
 */
public class Club
{
    // instance variables - replace the example below with your own
    private Date openingTime;
    private Date closingTime;
    private List<Member> members;
    private List<Sport> sports;
    
    /**
     * Constructor for objects of class Club
     * This constructor recieves no parameters and it initialize the member and sport
     * arraylist.
     */
    public Club()
    {
        // initialise instance variables
        if(members == null)
            members = new ArrayList<Member>();
        sports = new ArrayList<Sport>();
    }
    
    
    /**
     * This method finds member from member list.
     * 
     * @param memberNumber The member Number of member
     * @return Member .It returns the member object
     */
    public Member findMember(String memberNumber){
        for(Member element : members){
            if(element.getMemberNumber().equals(memberNumber))
                return element;
        }
        return null;
    }
    
    /**
     * This method add member to the member list.
     * 
     * @param Member .The member Object
     * @return Boolean .It returns true if member is added and false if member is not added 
     * or if member exists already  
     */
    public boolean addMember(Member member){
        if(validateMember(member))
            if(this.members.add(member))
                return true;
        return false;        
    }
    
    /**
     * This method returns the list of member in arraylist.
     * 
     * 
     * @return MemberList .It returns the arraylist of members
     */
    public List<Member> getMember(){
        return members;
    }    
    
    /**
     * This method add sport to the club
     * 
     * @param Sport. The Sport Object
     * @return Boolean. It returns true if sport is added and false if not added.
     */
    public boolean addSport(Sport sport){
        if(this.sports.add(sport))
            return true;
        return false;
    }
    
    /**
     * This method returns the list of sports the club support
     * 
     * 
     * @return Sport List. It returns the list of sport.
     */
    public List<Sport> getSport(){
        return sports;
    }
    
    
   /**
     * This method finds the specific sport in the club and returns it.
     * 
     * @param sportName .The sportName is a string and the name of the sport supported by club.
     * @return Sport.It returns the Sport object
     */
    public Sport getSport(String sportName){
        String content = "";
        for(Sport element : sports){
            if(element.getName().equals(sportName))
                return element;
        }
        return null;
    }    
    
    /**
     * This method validate the member list and return false if member already exist and
     * true if there is no member;
     * 
     * @param memberNumber The member Number of member
     * @return Member .It returns the member object
     */
    private boolean validateMember(Member member){
        for(Member element : members){
            if(element.getMemberNumber().equals(member.getMemberNumber()))
                return false;
        }
        return true;
    }
    
    public void setOpeningTime(Date openingTime){
        this.openingTime = openingTime;
    }
    
    public Date getOpeningTime(){
        return openingTime;
    }
    
    public void setClosingTime(Date closingTime){
        this.closingTime = closingTime;
    }
    
    public Date getClosingTime(){
        return closingTime;
    }
    
    public boolean isDateInFuture(String dateInString){
        Date currentDate = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date proposedDate = null;
        try{
             proposedDate = sdf1.parse(dateInString);
        }catch(ParseException e){
            e.printStackTrace();
        }
        GregorianCalendar currentCalendar = new GregorianCalendar();
        currentCalendar.setTime(currentDate);
        GregorianCalendar proposedCalendar = new GregorianCalendar();
        proposedCalendar.setTime(proposedDate);
        
        currentCalendar.set(Calendar.HOUR_OF_DAY, 0);
        currentCalendar.set(Calendar.MINUTE, 0);
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);
        //System.out.println(currentCalendar.getTime());
        //System.out.println(proposedCalendar.getTime());
        int result = proposedCalendar.compareTo(currentCalendar);
        if(result == 0 || result == 1)
            return true;
        else
            return false;
    }
    
    public boolean isTimeInFuture(String dateInString, String timeInString){
        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date proposedStartDate = new Date();
        try{
            proposedStartDate = sdfTime.parse(dateInString+" "+timeInString);
        }catch(ParseException e){
            e.printStackTrace();
        }
        GregorianCalendar proposedCalendar = new GregorianCalendar();
        proposedCalendar.setTime(proposedStartDate);
        
        GregorianCalendar currentCalendar = new GregorianCalendar();
        currentCalendar.set(Calendar.SECOND, 0);
        currentCalendar.set(Calendar.MILLISECOND, 0);
        
        //System.out.println(currentCalendar.getTime());
        //System.out.println(proposedCalendar.getTime());
        
        int result = proposedCalendar.compareTo(currentCalendar);
        if(result == 0 || result == 1)
            return true;
        else
            return false;
    }
    
    public boolean checkBookingAgainstOpeningAndClosingTime(String timeInString){
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
        Date proposedOpeningTime = new Date();
        try{
            proposedOpeningTime = sdfTime.parse(timeInString);
        }catch(ParseException e){
            e.printStackTrace();
        }
        GregorianCalendar proposedCalendar = new GregorianCalendar();
        proposedCalendar.setTime(proposedOpeningTime);
        
        Date clubOpeningTime = this.getOpeningTime();
        GregorianCalendar clubOpeningCalendar = new GregorianCalendar();
        clubOpeningCalendar.setTime(clubOpeningTime);
        
        //System.out.println(clubOpeningCalendar.getTime());
        //System.out.println(proposedCalendar.getTime());
        
        int result = proposedCalendar.compareTo(clubOpeningCalendar);
        if(result == -1)
            return false;
        else{
            Date clubClosingTime = this.getClosingTime();
            GregorianCalendar clubClosingCalendar = new GregorianCalendar();
            clubClosingCalendar.setTime(clubClosingTime);
            
            result = proposedCalendar.compareTo(clubClosingCalendar);
            if(result == -1)
                return true;
            else
                return false;
        }
    }
    
    public boolean checkBookingDuration(Sport sport, int duration){
        if(sport.getName().equalsIgnoreCase("tennis")){
            if(duration > 120)
                return false;
            else
                return true;
        }
        else if(sport.getName().equalsIgnoreCase("squash")){
            if(duration > 60)
                return false;
            else
                return true;
        }
        else
            return false;
    }
    
    public void addSportAndCourtToClub() throws Exception{
        String filename = "sport.txt";
        List<String> data = new ArrayList<String>();
        data = IO_Support.readData(filename);
        //System.out.println(data);
        for(String element : data){
            String[] token = element.split(",");
            String name = token[0];
            double usageFee = Double.parseDouble(token[1]);
            double insuranceFee = Double.parseDouble(token[2]);
            double affiliationFee = Double.parseDouble(token[3]);
            int courtIndex = 4;
            if(name.equalsIgnoreCase("Tennis")){
                Sport sportTennis = new Tennis(name);
                sportTennis.setUsageFees(usageFee);
                sportTennis.setInsuranceFees(insuranceFee);
                sportTennis.setAffiliationFees(affiliationFee);
 
                for(int i = courtIndex; i < token.length; i++){
                    int courtNumber = Integer.parseInt(token[i]);
                    Court court = new Court(courtNumber);
                    sportTennis.addCourt(court);
                }
                addSport(sportTennis);
            }
            else if(name.equalsIgnoreCase("Squash")){
                Sport sportSquash = new Squash(name);
                sportSquash.setUsageFees(usageFee);
                sportSquash.setInsuranceFees(insuranceFee);
                sportSquash.setAffiliationFees(affiliationFee);
                for(int i = courtIndex; i < token.length; i++){
                    int courtNumber = Integer.parseInt(token[i]);
                    Court court = new Court(courtNumber);
                    sportSquash.addCourt(court);
                }
                addSport(sportSquash);
            }
        }
    }
    
    public void addMemberToClub() throws Exception{
        String filename = "member.txt";
        Member member = null;
        List<String> data = new ArrayList<String>();
        data = IO_Support.readData(filename);
        for(String element : data){
            String[] token = element.split(",");
            String memberNumber = token[0];
            String memberName = token[1];
            if(token[2].equalsIgnoreCase("true") || token[2].equalsIgnoreCase("false")){
                boolean financial = Boolean.parseBoolean(token[2]);
                member = new Member(memberNumber, memberName, financial);
                int sportIndex = 3;
                //System.out.println(token.length);
                if(token.length > 3){
                    for(int i = sportIndex; i < token.length; i++){
                        //System.out.println("play tennis");
                        if(token[i].equalsIgnoreCase("Tennis")){
                            Sport sport = new Tennis(token[i]);
                            member.addMemberSports(sport);
                        }
                        else if(token[i].equalsIgnoreCase("Squash")){
                            Sport sport = new Squash(token[i]);
                            member.addMemberSports(sport);
                        }
                    }
                }
            }
            if(member != null)
                addMember(member);
        }
    }
    
    public void loadBooking(){
        Serializer serializer = new Serializer();
        List<Booking> bookingList = serializer.deserializeBooking("booking.txt");
        if(!bookingList.isEmpty()){
            System.out.println(bookingList);
            for(Booking b : bookingList){
                Member member = findMember(b.getBookedBy().getMemberNumber());
                member.addMemberBooking(b);
            }
        }
        
    }
}
