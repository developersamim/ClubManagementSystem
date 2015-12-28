
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class IO_Support
{
    private static Scanner in = new Scanner(System.in);
    
    public static String getString(String prompt)
    {
        System.out.print(prompt + " ");
        return in.nextLine();
    }
    
    public static Double getDouble(String prompt)
    {
        Double d = 0.00;
        while(true)
        {
            try
            {
                System.out.print(prompt + " ");
                d = Double.parseDouble(in.nextLine());
                break;
            }
            catch(Exception e)
            {
                  System.out.println("Not a valid Double");
            }
        }
        return d;  
    }
    
     public static Integer getInteger(String prompt)
    {
        Integer i = 0;
        while(true)
        {
            try
            {
                System.out.print(prompt + " ");
                i = Integer.parseInt(in.nextLine());
                break;
            }
            catch(Exception e)
            {
                System.out.println("Not a valid Integer");
            }
        }
        return i;  
    }
    
    public static void println(String toPrint)
    {
        System.out.println(toPrint);
    }
    
    
    
    public static void saveData(String fileName, List<String> data) throws IOException
    {
            PrintWriter pw = new PrintWriter(new FileWriter(fileName));
            for(String s : data)
            {
                pw.println(s);
            }
            pw.close();
    }
    
    public static ArrayList<String> readData(String fileName) throws Exception
    {
        ArrayList<String> data = new ArrayList<String>();
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        
        String temp = in.readLine(); 
        while (temp != null)
        {
            data.add(temp);
            temp = in.readLine();
        }
        in.close();
        return data;
    }
    
    public static Integer getInteger()
    {
        Integer i = 0;
        while(true)
        {
            try
            {
                System.out.print("Please enter an integer");
                i = Integer.parseInt(in.nextLine());
                break;
            }
            catch(Exception e)
            {
                System.out.println("Not a valid Integer");
            }
        }
        return i;  
    }
    
    public static boolean isValidDate(String dateInString){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            sdf.parse(dateInString);
            return true;
        }catch(ParseException e){
            return false;
        }
    }
     
    public static boolean isValidTime(String timeInString){
        final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern pattern = Pattern.compile(TIME24HOURS_PATTERN);
        Matcher matcher = pattern.matcher(timeInString);
        return matcher.matches();
    }
    
    public static boolean checkDate(String dateInString){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date proposedDate = null;
        try{
            proposedDate = sdf.parse(dateInString);
        }catch(ParseException e){
            e.printStackTrace();
        }
        GregorianCalendar proposedCalendar = new GregorianCalendar();
        proposedCalendar.setTime(proposedDate);
        
        
        GregorianCalendar endCalendar = new GregorianCalendar();
        // Increment the calendar's date by 7 day.
        endCalendar.add(Calendar.DAY_OF_MONTH, 7);
        endCalendar.set(Calendar.HOUR_OF_DAY, 0);
        endCalendar.set(Calendar.MINUTE, 0);
        endCalendar.set(Calendar.SECOND, 0);
        endCalendar.set(Calendar.MILLISECOND, 0);
        
        //System.out.println(proposedCalendar.getTime());
        //System.out.println(endCalendar.getTime());
        
        int result = proposedCalendar.compareTo(endCalendar);
        if(result == -1)
            return true;
        else
            return false;
    }

}// end of class

