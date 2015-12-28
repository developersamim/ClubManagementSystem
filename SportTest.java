

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SportTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SportTest
{
    private Sport s1;
    private Court c1;
    
    /**
     * Default constructor for test class SportTest
     */
    public SportTest()
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
        s1 = new Tennis("tennis");
        c1 = new Court(40);
    }
    
    @Test
    public void addCourt(){
        s1.addCourt(c1);
    }
    
    @Test
    public void getCourt(){
        s1.getCourt();
    }
    
    @Test
    public void getCourtObject(){
        s1.getCourt(40);
    }
    
    @Test
    public void setName(){
        s1.setName("tennis");
    }
    @Test
    public void getName(){
        s1.getName();
    }
    @Test
    public void setUsageFees(){
        s1.setUsageFees(200.00);
    }
    @Test
    public void getUsageFees(){
        s1.getUsageFees();
    }
    @Test
    public void setInsuranceFees(){
        s1.setInsuranceFees(100.00);
    }
    @Test 
    public void getInsuranceFees(){
        s1.getInsuranceFees();
    }
    @Test
    public void setAffiliationFees(){
        s1.setAffiliationFees(50.00);
    }
    @Test
    public void getAffiliationFees(){
        s1.getAffiliationFees();
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
