

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.TimeZone;
/**
 * The test class BookingTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BookingTest
{
    private Member m1;
    private Court c1;
    private Booking b1;
    Date startDate;
    Date endDate;
    int duration;
    /**
     * Default constructor for test class BookingTest
     */
    public BookingTest()
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
        m1 = new Member("01","sam",true);
        c1 = new Court(40);
        b1 = new Booking();
        
        startDate = new Date();
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
        
        duration = 120;
        long t = startDate.getTime();
        endDate = new Date(t + (120 * ONE_MINUTE_IN_MILLIS));
    }
    
    @Test
    public void clash(){
        b1.checkClash(b1);
    }
    @Test
    public void setBookedBy(){
        b1.setBookedBy(m1);
    }
    
    @Test 
    public void getBookedBy(){
        b1.getBookedBy();
    }
    @Test
    public void setCourt(){
        b1.setCourt(c1);
    }
    @Test
    public void getCourt(){
        b1.getCourt();
    }
    @Test 
    public void setStartDate(){
        b1.setStartDate(startDate);
    }
    @Test
    public void getStartDate(){
        b1.getStartDate();
    }
    @Test
    public void setEndDate(){
        b1.setEndDate(endDate);
    }
    @Test
    public void getEndDate(){
        b1.getEndDate();
    }
    @Test
    public void setDuration(){
        b1.setDuration(duration);
    }
    @Test
    public void getDuration(){
        b1.getDuration();
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
