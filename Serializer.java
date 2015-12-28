import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class Serializer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Serializer
{
    // instance variables - replace the example below with your own
    List<Booking> bookingList;

    /**
     * Constructor for objects of class Serializer
     */
    public Serializer()
    {
        bookingList = new ArrayList<Booking>();
    }
    
    public List<Booking> deserializeBooking(String filename)
    {
        try{
            FileInputStream fin = new FileInputStream(filename);
    		ObjectInputStream ois = new ObjectInputStream(fin);
    		bookingList = (ArrayList<Booking>) ois.readObject();
    		ois.close();
    		return bookingList;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public void serializeBooking(List<Booking> obj, String filename){
        try{
            FileOutputStream fout = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(obj);
            oos.close();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
