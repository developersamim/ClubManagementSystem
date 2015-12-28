import java.util.*;
import java.text.*;
/**
 * Write a description of class Start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Start
{
    private static Club club;
    
    public static void main(String[] args) throws Exception{
        club = new Club();        
        
        // rough work starts here
        club.addSportAndCourtToClub();
        club.addMemberToClub();
        club.loadBooking(); // loadBooking must be called after addMemberToClub.
        
        //System.out.println(club.findMember("123").getMemberSports());
        //System.out.println(club.findMember("123"));
      
        //System.out.println(club.getMember());
        // rough work ends here
        
        //ready the system starts here
        //setSportAndCourt();
        
        //addMember();
        setOpeningAndClosingTimeOfClub("08:00", "23:00");
        //ready the system ends here
        
        UserInterface UI = new UserInterface(club);
        UI.run();
        //MyGUI gui = new MyGUI(club);
        //rough starts here
        
        // rough ends here
    }
    
    public static void setOpeningAndClosingTimeOfClub(String opening, String closing){
        Date openingTime = new Date();
        Date closingTime = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");      
        try{
            openingTime = sdf1.parse(opening);
            closingTime = sdf1.parse(closing);
        }catch(ParseException e){
            e.printStackTrace();
        }
        club.setOpeningTime(openingTime);
        club.setClosingTime(closingTime);
    }
    
}
