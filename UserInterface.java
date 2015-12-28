import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.TimeZone;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.io.IOException;
import java.io.Serializable;
/**
 * Write a description of class UserInterface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UserInterface
{
    private Club club;
    private Member member;
    String bookingFile = "booking.txt";
    
    public UserInterface(Club club){
        this.club = club;
        //setSportAndCourt();
        //addMember();
        //setOpeningAndClosingTimeOfClub("08:00", "23:00");
    }
    
    public void run()
    {
        while(true)
            switch (menu() ) 
            {
                    case 1:
                        makeBooking();
                        break;
                    case 2:
                        makeBlockBooking();
                        break;
                    case 3:
                        deleteBooking();
                        break;
                     case 4:
                        showMemberBooking();
                        break;
                     case 5:
                        showCourtBooking();
                        break;
                     case 6:
                        showAvailableCourt();
                        break;
                     case 7:
                        saveBooking();
                        System.exit(0);
                     default:
                        System.out.println ( "Unrecognized option" );
                        break;
            }
    }
    
    private int menu()
    {   
        System.out.println("1) Make Booking For Member\n2) Make a Block Booking\n3) Delete a Booking\n4) Show member bookings\n5) Show Court Bookings\n6) Show Available Courts\n7) Exit " );
        return IO_Support.getInteger("Select Option: ");
    }
    
    public void saveBooking(){
        // add booking to a file starts here
        List<Booking> booked = new ArrayList<Booking>();
        if(member != null){
            booked = member.getMemberBooking();
            Serializer serializer = new Serializer();
            serializer.serializeBooking(booked, "booking.txt");
        }        
        // add booking to a file ends here
    }
    
    public void showCourtBooking(){
        Sport sport = null;
        List<Court> listOfCourt = new ArrayList<Court>();
        List<Booking> listOfBooking = new ArrayList<Booking>();
        
        // check member play sport starts here
        
        boolean sportFlag = true;
        while(sportFlag){
            switch(getSportFromUser()){ // list the sport supported by club
                case 1:
                    sport = club.getSport("Tennis");
                    sportFlag = false;
                    break;
                case 2:
                    sport = club.getSport("Squash");
                    sportFlag = false;
                    break;
                default:
                    System.out.println(" Unrecognized Option ");
                    break;
            }
        }
        
        boolean courtFlag = true;
        int userCourt;
        listOfCourt = sport.getCourt();
        Court court = null;
        while(courtFlag){
            for(Court element : listOfCourt){
                System.out.println(element.getCourtNumber());
            }
            
            userCourt = IO_Support.getInteger("Enter the court number from the list");

            court = sport.getCourt(userCourt);
            if(court != null)
                courtFlag = false;
            else
                System.out.println("Court doesnt exist. Please enter valid court number");
        }
        
        listOfBooking = court.getCourtBooking();
        if(!listOfBooking.isEmpty()){
            System.out.println("----- Court Bookings -----");
            int counter = 0;
            for(Booking element : listOfBooking){       
                counter++;
                System.out.println(counter+". Member Number: "+element.getBookedBy().getMemberNumber()+
                                    " Member Name: "+element.getBookedBy().getMemberName()+
                                    " Court Number: "+element.getCourt().getCourtNumber()+
                                    " Start Date: "+element.getStartDate()+
                                    " End Date: "+element.getEndDate());
            }
        }else{
            System.out.println("This court has no booking to show");
        }
        
        // check member play sport ends here
    }
    
    public void showMemberBooking(){
        List<Booking> memberBookingList = new ArrayList<Booking>();
        Booking booking = null;
        System.out.println("Enter Member Number");
        Scanner input = new Scanner(System.in);
        String memberNumber = input.nextLine();
        member = club.findMember(memberNumber);
        
        if(member == null){
            System.out.println("Member Number not found");
            return;
        }
        
        memberBookingList = member.getMemberBooking();
        System.out.println("----- Member Bookings -----");
        int counter = 0;
        for(Booking element : memberBookingList){       
            counter++;
            System.out.println(counter+". Member Number: "+element.getBookedBy().getMemberNumber()+
                                " Member Name: "+element.getBookedBy().getMemberName()+
                                " Court Number: "+element.getCourt().getCourtNumber()+
                                " Start Date: "+element.getStartDate()+
                                " End Date: "+element.getEndDate());
        }
        //System.out.println(memberBookingList.size());
    }
    
    public void deleteBooking(){
        //get member number from user
        List<Booking> memberBookingList = new ArrayList<Booking>();
        
        System.out.println("Enter Member Number");
        Scanner input = new Scanner(System.in);
        String memberNumber = input.nextLine();
        member = club.findMember(memberNumber);
        if(member == null){
            System.out.println("Member Number not found");
            return;
        }
        
        memberBookingList = member.getMemberBooking();
        System.out.println("----- Member Bookings -----");
        int counter = 0;
        for(Booking element : memberBookingList){       
            counter++;
            System.out.println(counter+". Member Number: "+element.getBookedBy().getMemberNumber()+
                                " Member Name: "+element.getBookedBy().getMemberName()+
                                " Court Number: "+element.getCourt().getCourtNumber()+
                                " Start Date: "+element.getStartDate()+
                                " End Date: "+element.getEndDate());
        }
        
        boolean bookingFlag = true;
        int userBooking;
        Booking dBooking = null;
        while(bookingFlag){
            userBooking = IO_Support.getInteger("Enter the number of booking you want to delete");
            if(userBooking <= memberBookingList.size()){
                int cnt = 0;
                for(Booking element : memberBookingList){
                    cnt++;
                    if(cnt == userBooking){
                        dBooking = element;
                        break;
                    }
                }
                if(dBooking != null){
                    member.deleteBooking(dBooking);
                    dBooking.getCourt().deleteBooking(dBooking);
                    System.out.println("Delete Booking Successfull");
                    bookingFlag = false;
                }
            }else{
                System.out.println("Please enter the valid number");
            }
        }
        
        
    }
    
    public void showMember(){
        List<Member> memberList = new ArrayList<Member>();
        
        memberList = club.getMember();
        for(Member element : memberList){
            String content = "";
            content += "Member Number: "+element.getMemberNumber();
            content += " Member Name: "+element.getMemberName();
            //content += " Sport Played: "+element.getMemberSports().size();
            for(Sport sportElement : element.getMemberSports()){
                content += " Member Sport: "+sportElement.getName()+" Court: ";
                //if(sportElement.getName() == 
                for(Court courtElement : sportElement.getCourt()){
                    content += courtElement.getCourtNumber()+",";
                }
            }
        }
    }
    
    
    
    public void makeBlockBooking(){
        List<Court> listOfCourt = new ArrayList<>();
        String sportName;
        Sport sport = null;
        final long ONE_MINUTE_IN_MILLIS = 60000;
        Date proposedStartDate = new Date();
        
        //take sport from user
        boolean sportFlag = true;
        while(sportFlag){
            switch(getSportFromUser()){ // list the sport supported by club
                case 1:
                    sport = club.getSport("tennis");
                    sportFlag = false;
                    break;
                case 2:
                    sport = club.getSport("squash");
                    sportFlag = false;
                    break;
                default:
                    System.out.println(" Unrecognized Option ");
                    break;
            }
        }
        
        
        
        
        //take a date from the user
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Australia/Melbourne"));
        boolean dateFlag = true;
        String dateInString = null;
        while(dateFlag){
            //System.out.println("Enter the date you want to play");
            dateInString = IO_Support.getString("Enter the date you want to play in the format yyyy-mm-dd");
            if(IO_Support.isValidDate(dateInString)){
                if(!club.isDateInFuture(dateInString)){
                    System.out.println("Please enter the date of today or future but not in the past");
                }else{
                    // mem cay only book up to seven days in advance
                    boolean response = IO_Support.checkDate(dateInString);
                    if(response == true)
                        dateFlag = false;
                    else
                        System.out.println("Please Enter the date with in seven days. Member may only book up to seven days in advance.");
                }
            }else{
                System.out.println("Wrong date format. Please enter the date in the format yyyy-mm-dd");
            }
        }
        
        boolean timeFlag = true;
        String timeInString = null;
        while(timeFlag){
            timeInString = IO_Support.getString("Enter the time you want to play in the format HH:mm");
            if(IO_Support.isValidTime(timeInString)){
                //check time is in future or current
                boolean response = club.isTimeInFuture(dateInString, timeInString);
                if(response){
                    //check time against opening and closing time of club
                    boolean responseTime = club.checkBookingAgainstOpeningAndClosingTime(timeInString);
                    if(responseTime)
                        timeFlag = false;
                    else
                        System.out.println("The Club operates only between 08:00 and 23:00");
                }else{
                    System.out.println("Please enter the time in future or current but not of past");
                }
            }
            else{
                System.out.println("Wrong time format. Please enter the time in the format HH:mm");
            }
        }
        
        // get the duration of the play from the user
        boolean durationFlag = true;
        int userDuration = 0;
        while(durationFlag){
            userDuration = IO_Support.getInteger("Enter the number of minutes you want to play");
                durationFlag = false;
        }
        
        try{
            proposedStartDate = sdf.parse(dateInString+" "+timeInString+":00");     
        }catch(ParseException e){
            e.printStackTrace();
        }
        
        
        
        // for end date
        long t = proposedStartDate.getTime();
        Date proposedEndDate = new Date(t + (userDuration * ONE_MINUTE_IN_MILLIS));
        
        boolean courtFlag = true;
        listOfCourt = sport.getCourt();
        int userTotalCourt = 0;
        while(courtFlag){
            userTotalCourt = IO_Support.getInteger("Enter the number of courts you want to book but less than "+listOfCourt.size());
            if(userTotalCourt <= listOfCourt.size())
                courtFlag = false;
        }
        
        
        
        
        // make a booking object starts here
        /*Booking proposedBooking = new Booking();
        proposedBooking.setBookedBy(member);
        proposedBooking.setStartDate(proposedStartDate);
        proposedBooking.setEndDate(proposedEndDate);
        proposedBooking.setCourt(court);*/
        
    }
    
    public int getSportFromUser(){
        // Get all the sport from the club
        List<Sport> sportList = new ArrayList<Sport>();
        sportList = club.getSport();
        int count = 0;
        for(Sport element: sportList){
            count++;
            System.out.println(count+". "+element.getName());
        }
        return IO_Support.getInteger("Select Option [1-2] or Press [0] to exit: ");
    }
    
    public void showAvailableCourt(){
        List<Court> listOfCourt = new ArrayList<>();
        String sportName;
        Sport sport = null;
        List<Court> availableCourt = new ArrayList<Court>();
        final long ONE_MINUTE_IN_MILLIS = 60000;
        Date proposedStartDate = new Date();
        
        //get member number from user
        System.out.println("Enter Member Number");
        Scanner input = new Scanner(System.in);
        String memberNumber = input.nextLine();
        member = club.findMember(memberNumber);
        if(member == null){
            System.out.println("Member Number not found");
            return;
        }
        
         // check member play sport starts here
        boolean sportFlag = true;
        while(sportFlag)
            switch(getSportFromUser()){ // list the sport supported by club
                case 1:
                    sport = club.getSport("Tennis");
                    sportFlag = false;
                    break;
                case 2:
                    sport = club.getSport("Squash");
                    System.out.println(sport);
                    sportFlag = false;
                    break;
                case 0:
                    run();
                default:
                    System.out.println(" Unrecognized Option ");
                    break;
            }
        
            //take a date from the user
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Australia/Melbourne"));
        boolean dateFlag = true;
        String dateInString = null;
        while(dateFlag){
            //System.out.println("Enter the date you want to play");
            dateInString = IO_Support.getString("Enter the date you want to play in the format yyyy-mm-dd");
            if(IO_Support.isValidDate(dateInString)){
                if(!club.isDateInFuture(dateInString)){
                    System.out.println("Please enter the date of today or future but not in the past");
                }else{
                    // mem cay only book up to seven days in advance
                    boolean response = IO_Support.checkDate(dateInString);
                    if(response == true)
                        dateFlag = false;
                    else
                        System.out.println("Please Enter the date with in seven days. Member may only book up to seven days in advance.");
                }
            }else{
                System.out.println("Wrong date format. Please enter the date in the format yyyy-mm-dd");
            }
        }
        
        
        
        
        boolean timeFlag = true;
        String timeInString = null;
        while(timeFlag){
            timeInString = IO_Support.getString("Enter the time you want to play in the format HH:mm");
            if(IO_Support.isValidTime(timeInString)){
                //check time is in future or current
                boolean response = club.isTimeInFuture(dateInString, timeInString);
                if(response){
                    //check time against opening and closing time of club
                    boolean responseTime = club.checkBookingAgainstOpeningAndClosingTime(timeInString);
                    if(responseTime)
                        timeFlag = false;
                    else
                        System.out.println("The Club operates only between 08:00 and 23:00");
                }else{
                    System.out.println("Please enter the time in future or current but not of past");
                }
            }
            else{
                System.out.println("Wrong time format. Please enter the time in the format HH:mm");
            }
        }
        
        // get the duration of the play from the user
        boolean durationFlag = true;
        int userDuration = 0;
        while(durationFlag){
            userDuration = IO_Support.getInteger("Enter the number of minutes you want to play");
            if(club.checkBookingDuration(sport, userDuration)){
                durationFlag = false;
            }else{
                System.out.println("Member can only book squash court for up to one hour and tennis court for up to two hour");
            }
        }
        
        try{
            proposedStartDate = sdf.parse(dateInString+" "+timeInString+":00");     
        }catch(ParseException e){
            e.printStackTrace();
        }
        
        
        
        // for end date
        long t = proposedStartDate.getTime();
        Date proposedEndDate = new Date(t + (userDuration * ONE_MINUTE_IN_MILLIS));
        
        // make a booking object starts here
        Booking proposedBooking = new Booking();
        proposedBooking.setBookedBy(member);
        proposedBooking.setStartDate(proposedStartDate);
        proposedBooking.setEndDate(proposedEndDate);
        //proposedBooking.setCourt(court);
        
        availableCourt = sport.getAvailableCourt(proposedBooking);
        for(Court element : availableCourt){
            System.out.println(element.getCourtNumber());
        }
        
    }
    
    public void makeBooking(){
        member = null;
        List<Court> listOfCourt = new ArrayList<>();
        String sportName;
        Sport sport = null;
        List<Court> availableCourt = new ArrayList<Court>();
        final long ONE_MINUTE_IN_MILLIS = 60000;
        Date proposedStartDate = new Date();
        
        //get member number from user
        
        boolean memberFlag = true;
        String memberNumber;
        while(memberFlag){
            memberNumber = IO_Support.getString("Enter Member Number. Press[x] to exit");
            if(memberNumber.isEmpty()){
                System.out.println("Please enter valid Member Number. Or Press [x] to exit");
            }
            else if(memberNumber.equalsIgnoreCase("x")){
                run();
            }
            else{
                member = club.findMember(memberNumber);
                if(member != null){
                    if(!member.getMemberFinancial()){
                        System.out.println("Member is not financial");
                    }else{
                        memberFlag = false;
                    }
                }    
                else
                    System.out.println("Member Number does not exist");
            }
        }
        
        
        // check member play sport starts here
        boolean sportFlag = true;
        while(sportFlag)
            switch(getSportFromUser()){ // list the sport supported by club
                case 1:
                    sport = club.getSport("Tennis");
                    if(sport == null){
                        System.out.println("This member does not play any sport");
                    }
                    else if(!member.checkSportPlayed(sport)){
                        System.out.println("Member do not play "+sport.getName());
                    }
                    else
                        sportFlag = false;
                    break;
                case 2:
                    sport = club.getSport("Squash");
                    if(sport == null){
                        System.out.println("This member does not play any sport");
                    }
                    else if(!member.checkSportPlayed(sport)){
                        System.out.println("Member do not play "+sport.getName());
                    }
                    else
                        sportFlag = false;
                    break;
                case 0:
                    run();
                default:
                    System.out.println(" Unrecognized Option ");
                    break;
            }
        
        //take a date from the user
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Australia/Melbourne"));
        boolean dateFlag = true;
        String dateInString = null;
        while(dateFlag){
            //System.out.println("Enter the date you want to play");
            dateInString = IO_Support.getString("Enter the date in the format [yyyy-mm-dd] or Press [x] to exit");
            if(dateInString.equalsIgnoreCase("x")){
                run();
            }
            else if(IO_Support.isValidDate(dateInString)){
                if(!club.isDateInFuture(dateInString)){
                    System.out.println("Please enter the date of today or future but not in the past");
                }else{
                    // mem cay only book up to seven days in advance
                    boolean response = IO_Support.checkDate(dateInString);
                    if(response == true)
                        dateFlag = false;
                    else
                        System.out.println("Please Enter the date with in seven days. Member may only book up to seven days in advance.");
                }
            }else{
                System.out.println("Wrong date format. Please enter the date in the format [yyyy-mm-dd]");
            }
        }
        
        
        
        
        boolean timeFlag = true;
        String timeInString = null;
        while(timeFlag){
            timeInString = IO_Support.getString("Enter the time you want to play in the format [HH:mm] or Press [x] to exit");
            if(dateInString.equalsIgnoreCase("x")){
                run();
            }
            else if(IO_Support.isValidTime(timeInString)){
                //check time is in future or current
                boolean response = club.isTimeInFuture(dateInString, timeInString);
                if(response){
                    //check time against opening and closing time of club
                    boolean responseTime = club.checkBookingAgainstOpeningAndClosingTime(timeInString);
                    if(responseTime)
                        timeFlag = false;
                    else
                        System.out.println("The Club operates only between 08:00 and 23:00");
                }else{
                    System.out.println("Please enter the time in future or current but not of past");
                }
            }
            else{
                System.out.println("Wrong time format. Please enter the time in the format HH:mm");
            }
        }
        
        // get the duration of the play from the user
        boolean durationFlag = true;
        int userDuration = 0;
        while(durationFlag){
            userDuration = IO_Support.getInteger("Enter the number of minutes you want to play or Press [0] to exit");
            if(userDuration == 0){
                run();
            }else if(club.checkBookingDuration(sport, userDuration)){
                durationFlag = false;
            }else{
                System.out.println("Member can only book squash court for up to one hour and tennis court for up to two hour");
            }
        }
        
        try{
            proposedStartDate = sdf.parse(dateInString+" "+timeInString+":00");     
        }catch(ParseException e){
            e.printStackTrace();
        }
        
        
        
        // for end date
        long t = proposedStartDate.getTime();
        Date proposedEndDate = new Date(t + (userDuration * ONE_MINUTE_IN_MILLIS));
        
        // get court from the user
        boolean courtFlag = true;
        int userCourt = 0;
        
        listOfCourt = sport.getCourt();
        Court court = null;
        while(courtFlag){
            String courts = "[";
            for(Court element : listOfCourt){
                courts += element.getCourtNumber()+", ";
                //System.out.println(element.getCourtNumber());
            }
            
            userCourt = IO_Support.getInteger("Enter the court number "+courts+"] from the list or Press [0] to exit");
            if(userCourt == 0){
                run();
            }
            court = sport.getCourt(userCourt);
            if(court != null)
                courtFlag = false;
            else
                System.out.println("Court doesnt exist. Please enter valid court number");
        }
        
        // make a booking object starts here
        Booking proposedBooking = new Booking();
        proposedBooking.setBookedBy(member);
        proposedBooking.setStartDate(proposedStartDate);
        proposedBooking.setEndDate(proposedEndDate);
        proposedBooking.setCourt(court);
        
        // check clash in court
        //System.out.println(court1.checkClash(proposedBooking));
        boolean bookingMade = false;
        boolean clash = court.checkClash(proposedBooking);
        if(clash){
            member.addMemberBooking(proposedBooking);            
            court.addCourtBooking(proposedBooking);      
            // add booking to a file starts here
            List<Booking> booked = new ArrayList<Booking>();
            booked = member.getMemberBooking();
            for(Booking b : booked){
                String bookedData = b.getBookedBy().getMemberNumber()+","+b.getCourt().getCourtNumber()+","+b.getStartDate()+","+b.getEndDate();
                List<String> bookedDataString = new ArrayList<String>();
                bookedDataString.add(bookedData);
                try{
                    IO_Support.saveData(bookingFile, bookedDataString);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }             
        // add booking to a file ends here
            System.out.println("HURRAY!! Booking is successfull");
            bookingMade = true;
        }else{
            System.out.println("Court Number: "+userCourt+" is not available");
        }
        
        availableCourt = sport.getAvailableCourt(proposedBooking);
        int userAvailableCourt;
        Court court1 = null;
        boolean courtExist = false;
        while(!bookingMade){
            String courts = "[";
            for(Court element : availableCourt){
                //System.out.println(element.getCourtNumber());
                courts += element.getCourtNumber()+", ";
            }
            
            userAvailableCourt = IO_Support.getInteger("Enter the Available court number "+courts+"] from the list or Press [0] to exit");
            if(userAvailableCourt == 0){
                run();
            }
            
            for(Court ele : availableCourt){
                if(ele.getCourtNumber() == userAvailableCourt){
                    courtExist = true;
                    break;
                }
            }
            if(courtExist)
                court1 = sport.getCourt(userAvailableCourt);
            if(court1 != null){
                proposedBooking.setCourt(court1);
                member.addMemberBooking(proposedBooking);
                court1.addCourtBooking(proposedBooking);
                
                System.out.println("HURRAY!! Booking is successfull");
                bookingMade = true;
            }
            else
                System.out.println("Court doesnt exist. Please enter valid court number");
        }
        
    }
    
    public void getCourtForSport(Sport sport){
        String output = "Court Number: ";
        for(Court element : sport.getCourt()){
            output += element.getCourtNumber()+",";
        }
        System.out.println(output);
    }
    
}
