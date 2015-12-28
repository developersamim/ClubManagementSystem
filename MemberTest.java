

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.TimeZone;

/**
 * The test class MemberTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MemberTest
{
    private Booking b1;
    private Booking b2;
    private Sport s1;
    private Sport s2;
    private Member m1;
    /**
     * Default constructor for test class MemberTest
     */
    public MemberTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        b1 = new Booking();
        b2 = new Booking();
        s1 = new Tennis("tennis");
        s2 = new Squash("squash");
        m1 = new Member("01","sam",true);
    }
    
    @Test
    public void addMemberBooking(){
        Date startDate = new Date();
        final long ONE_MINUTE_IN_MILLIS = 60000;
        String dateInString = "27/4/2015";
        String startTime = "10:00";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Australia/Melbourne"));
        try{
            startDate = sdf.parse(dateInString+" "+startTime+":00");     
        }catch(ParseException e){
            e.printStackTrace();
        }
        
        int duration = 120;
        long t = startDate.getTime();
        Date endDate = new Date(t + (120 * ONE_MINUTE_IN_MILLIS));
        
        b1.setBookedBy(m1);
        b1.setStartDate(startDate);
        b1.setEndDate(endDate);
        m1.addMemberBooking(b1);
    }
    
    @Test 
    public void addMemberSports(){
        m1.addMemberSports(s1);
    }
    @Test
    public void getMemberSports(){
        m1.getMemberSports();
    }
    
    @Test
    public void checkSportPlayed(){
        m1.checkSportPlayed(s1);
    }
    
    @Test
    public void getMemberBooking(){
        m1.getMemberBooking();
    }
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
