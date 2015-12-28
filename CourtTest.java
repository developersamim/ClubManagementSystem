

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CourtTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CourtTest
{
    private Court c1;
    private Booking b1;
    /**
     * Default constructor for test class CourtTest
     */
    public CourtTest()
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
        c1 = new Court(40);        
        b1 = new Booking();
    }
    @Test
    public void addCourtBooking(){
        c1.addCourtBooking(b1);
    }
    @Test 
    public void getCourtBooking(){
        c1.getCourtBooking();
    }
    @Test
    public void setCourtNumber(){
        c1.setCourtNumber(10);
    }
    @Test
    public void getCourtNumber(){
        c1.getCourtNumber();
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
