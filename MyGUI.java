import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.*;
import java.util.TimeZone;
/**
 * Write a description of class GUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyGUI
{
    // instance variables - replace the example below with your own
    private Club club;
    private Sport sport;
    private Member member;
    private Date proposedStartDate;
    private Date proposedEndDate;
    private JFrame frame;
    private JPanel center;
    private JPanel statusPanel;
    private JLabel statusLabel;
    private JComboBox<String> faceCombo;
    private JButton button2;
    private JButton button1;
    private JLabel lStartDate;
    private JTextField tStartDate;
    private JLabel lHintDate;
    private JLabel lInfoDate;
    private JLabel lStartTime;
    private JTextField tStartTime;
    private JLabel lHintTime;
    private JLabel lInfoTime;
    private JLabel lDuration;
    private JTextField tDuration;
    private JLabel lHintDuration;
    private JLabel lInfoDuration;
    private JLabel lAvailableCourt;
    private JComboBox<String> courtCombo;
    private String dateInString;
    private String timeInString;
    private JButton bAvailableCourt;
    private int userDuration;
    private boolean validate;
    
    /**
     * Constructor for objects of class GUI
     */
    public MyGUI(Club club)
    {
        // initialise instance variables
        this.club = club;
        makeFrame();
        createStatusPanel();
        validate = true;
        dateInString = null;
        timeInString = null;
        userDuration = 0;
    }
    
    public void makeFrame(){
        frame = new JFrame();
        frame.setTitle("Club GUI");
        frame.setSize(1000, 600);
        Container contentPane = frame.getContentPane();
        
        JPanel buttonPanel = new JPanel(new GridLayout(2,1));
        button1 = new JButton("Show Booking");
        
        button2 = new JButton("Make Booking");
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        JPanel east = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;
        east.add(buttonPanel, gbc);

        center = new JPanel(){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(550, 650);
            }
        };
        center.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        frame.add(east, BorderLayout.EAST);
        frame.add(center);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                showBooking(center);
            }
        });
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                makeBooking(center);
            }
        });
    }
    
    public void showBooking(JPanel center){
        JLabel label = new JLabel("I am show Booking");
        center.add(label);
        center.repaint();
    }
    
    public void makeBooking(JPanel center){
        final JPanel makeBookingPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel label = new JLabel("Member Number");
        makeBookingPanel.add(label, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        final JTextField tf1 = new JTextField(20);
        makeBookingPanel.add(tf1, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        final JButton b1 = new JButton("Validate Member Number");
        makeBookingPanel.add(b1, gbc);
        final JLabel lSport = new JLabel("Choose Sport");
        lSport.setVisible(false);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        makeBookingPanel.add(lSport, gbc);
        faceCombo = new JComboBox<>();
        faceCombo.addItem("Choose Sport");
        faceCombo.setPreferredSize(new Dimension(225, 20));
        faceCombo.setVisible(false);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 1;
        gbc.gridy = 1;
        makeBookingPanel.add(faceCombo, gbc);
        
        lStartDate = new JLabel("Start Date");
        lStartDate.setVisible(false);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        makeBookingPanel.add(lStartDate, gbc);
        tStartDate = new JTextField(20);
        tStartDate.setVisible(false);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 1;
        gbc.gridy = 2;
        makeBookingPanel.add(tStartDate, gbc);
        lHintDate = new JLabel("[yyyy-mm-dd]");
        lHintDate.setVisible(false);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 2;
        gbc.gridy = 2;
        makeBookingPanel.add(lHintDate, gbc);
        lInfoDate = new JLabel("");
        lInfoDate.setVisible(false);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 1;
        gbc.gridy = 3;
        makeBookingPanel.add(lInfoDate, gbc);
        
        
        lStartTime = new JLabel("Start Time");
        lStartTime.setVisible(false);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        makeBookingPanel.add(lStartTime, gbc);
        
        tStartTime = new JTextField(20);
        tStartTime.setVisible(false);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 1;
        gbc.gridy = 4;
        makeBookingPanel.add(tStartTime, gbc);
        
        lHintTime = new JLabel("[HH:mm]");
        lHintTime.setVisible(false);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 2;
        gbc.gridy = 4;
        makeBookingPanel.add(lHintTime, gbc);
        
        lInfoTime = new JLabel("");
        lInfoTime.setVisible(false);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 1;
        gbc.gridy = 5;
        makeBookingPanel.add(lInfoTime, gbc);
        
        
        lDuration = new JLabel("Duration");
        lDuration.setVisible(false);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 6;
        makeBookingPanel.add(lDuration, gbc);
        
        tDuration = new JTextField(20);
        tDuration.setVisible(false);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 1;
        gbc.gridy = 6;
        makeBookingPanel.add(tDuration, gbc);
        
        lHintDuration = new JLabel("Numbers between [1 - 120]");
        lHintDuration.setVisible(false);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 2;
        gbc.gridy = 6;
        makeBookingPanel.add(lHintDuration, gbc);
        
        lInfoDuration = new JLabel("");
        lInfoDuration.setVisible(false);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 1;
        gbc.gridy = 7;
        makeBookingPanel.add(lInfoDuration, gbc);
        
        bAvailableCourt = new JButton("Get Court");
        bAvailableCourt .setVisible(false);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 8;
        makeBookingPanel.add(bAvailableCourt, gbc);
        
        lAvailableCourt = new JLabel("Available Court");
        lAvailableCourt.setVisible(false);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 9;
        makeBookingPanel.add(lAvailableCourt, gbc);
        
        courtCombo = new JComboBox<>();
        courtCombo.addItem("Choose Court");
        courtCombo.setPreferredSize(new Dimension(225, 20));
        courtCombo.setVisible(false);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridx = 1;
        gbc.gridy = 9;
        makeBookingPanel.add(courtCombo, gbc);
        
        center.removeAll();
        center.add(makeBookingPanel);
        center.repaint();
        frame.setVisible(true);
        
        
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                String userMemberNumber = tf1.getText();
                
                member = club.findMember(userMemberNumber);
                if(member != null){
                    if(member.getMemberFinancial()){
                        statusLabel.setText("");
                        //JOptionPane.showMessageDialog(null, userMemberNumber);
                        // get sport from club
                        List<Sport> sportList = new ArrayList<Sport>();
                        sportList = club.getSport();
                        for(Sport element : sportList){
                            faceCombo.addItem(element.getName());
                        }
                        faceCombo.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                checkSport();
                            }
                        });
                        
                        makeBookingPanel.remove(b1);
                        tf1.setEditable(false);
                        faceCombo.setVisible(true);
                        lSport.setVisible(true);
                        makeBookingPanel.repaint();
                    }else{
                        statusLabel.setText("<html>Status : <font color='red'>Member is not Financial</font></html>");
                    }
                }else{
                    //JOptionPane.showMessageDialog(null, "Member Number doesnot exist");
                    statusLabel.setText("<html>Status : <font color='red'>Member Number doesnot exist</font></html>");
                }
            }
        });
    }
    public void loadDate(){
        lStartDate.setVisible(true);
        tStartDate.setVisible(true);
        lHintDate.setVisible(true);
        lInfoDate.setVisible(true);
        lStartTime.setVisible(true);
        tStartTime.setVisible(true);
        lHintTime.setVisible(true);
        lInfoTime.setVisible(true);
        lDuration.setVisible(true);
        tDuration.setVisible(true);
        lHintDuration.setVisible(true);
        lInfoDuration.setVisible(true);
        lAvailableCourt.setVisible(true);
        courtCombo.setVisible(true);
        bAvailableCourt.setVisible(true);
        
        bAvailableCourt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                boolean val = true;
                String msg = "";
                if(dateInString.equals("") && dateInString != null){
                    msg += "Start Date cannot be empty\n";
                    val = false;
                }
                if(timeInString.equals("") && dateInString != null){
                    msg += "Start Time cannot be empty\n";
                    val = false;
                }
                if(userDuration > 0 && dateInString != null){
                    msg += "Duration cannot be empty";
                    val = false;
                }
                if(val){
                    statusLabel.setText("");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    sdf.setTimeZone(TimeZone.getTimeZone("Australia/Melbourne"));
                     try{
                        proposedStartDate = sdf.parse(dateInString+" "+timeInString+":00");     
                    }catch(ParseException ex){
                        ex.printStackTrace();
                    }
                }else{
                    statusLabel.setText(msg);
                }
            }
        });
        
        
        // make a booking object
        
        /*Booking proposedBooking = new Booking();
        proposedBooking.setBookedBy(member);
        proposedBooking.setStartDate(proposedStartDate);
        proposedBooking.setEndDate(proposedEndDate);
        //proposedBooking.setCourt(court);
        
        availableCourt = sport.getAvailableCourt(proposedBooking);
        
        List<Court> availabeCourtList = new ArrayList<Court>();
        sportList = club.getSport();
        for(Sport element : sportList){
            faceCombo.addItem(element.getName());
        }
        faceCombo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                checkSport();
            }
        });
        */
        
        tDuration.addFocusListener(new FocusListener(){
            boolean validDuration = true;
            public void focusGained(FocusEvent e){
            }
            public void focusLost(FocusEvent e){
                userDuration = Integer.parseInt(tDuration.getText());
                if(!club.checkBookingDuration(sport, userDuration)){
                    lInfoDuration.setText("<html><font color='red'>Not valid number</font></html>");
                    validate = false;
                    validDuration = false;
                }
                if(validDuration == true){
                        lInfoDuration.setText("");
                }
            }
        });
        
        tStartTime.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e) {
                  //displayMessage("Focus gained", e);
            }
            public void focusLost(FocusEvent e){
                boolean validStartTime = true;
                timeInString = tStartTime.getText();
                if(IO_Support.isValidTime(timeInString)){
                    if(club.isTimeInFuture(tStartDate.getText(), timeInString)){
                        if(!club.checkBookingAgainstOpeningAndClosingTime(timeInString)){
                            lInfoTime.setText("<html><font color='red'>The Club operates only between 08:00 and 23:00</font></html>");
                            validStartTime = false;
                            validate = false;
                        }
                    }else{
                        lInfoTime.setText("<html><font color='red'>Enter time in future</font></html>");
                        validStartTime = false;
                        validate = false;
                    }
                }
                else
                {
                    lInfoTime.setText("<html><font color='red'>Wrong time format</font></html>");
                    validStartTime = false;
                    validate = false;
                }
                if(validStartTime == true){
                        lInfoTime.setText("");
                }
            }
        });
        
        tStartDate.addFocusListener(new FocusListener() {
            
              public void focusGained(FocusEvent e) {
                  //displayMessage("Focus gained", e);
              }
        
              public void focusLost(FocusEvent e) {
                  boolean validStartDate = true;
                  dateInString = tStartDate.getText();
                  if(IO_Support.isValidDate(dateInString))
                  {
                      if(!club.isDateInFuture(dateInString))
                      {
                          lInfoDate.setText("<html><font color='red'>Date is in past</font></html>");
                          validStartDate = false;
                          validate = false;
                      }else{
                            // mem cay only book up to seven days in advance
                            if(!IO_Support.checkDate(dateInString)){
                                lInfoDate.setText("<html><font color='red'>Member can only book up to seven days in advance</font></html>");
                                validStartDate = false;
                                validate = false;
                            }
                      }
                  }
                  else
                  {
                      lInfoDate.setText("<html><font color='red'>Wrong date format</font></html>");
                      validStartDate = false;
                      validate = false;
                  }
                  if(validStartDate == true){
                        lInfoDate.setText("");
                        
                  }
              }
        });
        
        
    }
    

    
    public void checkSport(){
        lStartDate.setVisible(false);
        tStartDate.setVisible(false);
        lHintDate.setVisible(false);
        lStartTime.setVisible(false);
        tStartTime.setVisible(false);
        lHintTime.setVisible(false);
        lDuration.setVisible(false);
        tDuration.setVisible(false);
        lHintDuration.setVisible(false);
        lInfoDuration.setVisible(false);
        lAvailableCourt.setVisible(false);
        courtCombo.setVisible(false);
        bAvailableCourt.setVisible(false);
        String userSport = (String)faceCombo.getSelectedItem();
        sport = club.getSport(userSport);
        if(!member.checkSportPlayed(sport)){
            statusLabel.setText("Member do not play this sport: "+sport.getName());
            return;
        }
        else{
            if(sport.getName().equalsIgnoreCase("Tennis"))
                lHintDuration.setText("Numbers between [1 - 120]");
            else
                lHintDuration.setText("Numbers between [1 - 60]");
        }
        loadDate();
        
    }
    
    public void createStatusPanel(){
        // create the status bar panel and shove it down the bottom of the frame
        statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        frame.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        statusLabel = new JLabel("status");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(statusLabel);
    }
}
