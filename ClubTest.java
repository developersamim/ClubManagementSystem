import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ClubTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ClubTest
{
    private Member m1;
    private Member m2;
    private Member m3;
    private Sport s1;
    private Sport s2;
    private Club club;
    
    /**
     * Default constructor for test class ClubTest
     */
    public ClubTest()
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
        
        m1 = new Member("01","samim",true);
        m2 = new Member("02","mason",true);
        m3 = new Member("01","selom", false);
        club = new Club();
        s1 = new Tennis("tennis");
        s2 = new Squash("squash");
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
    
    @Test
    public void addMember(){
        club.addMember(m1);
        club.addMember(m2);
        club.addMember(m3);
        club.addMember(m3);
        
    }
    
    @Test
    public void findMember(){
        addMember();
        club.findMember(m1.getMemberNumber());
    }
    
    @Test 
    public void addSport(){
        club.addSport(s1);
        club.addSport(s2);
    }
    
    @Test 
    public void getSport(){
        club.getSport();
    }
}

