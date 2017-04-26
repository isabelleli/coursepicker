/*ScheduleTabPanel.java
 CS 230 final project
 Modified by: ili
 Modified date: 04/22/17
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class ScheduleTabPanel extends JPanel {
  private JLabel searchResults; //text that displays course search results of user
  private JButton[][] buttons;
  private JLabel[] timeLabel;
  private JLabel[] weekdays;
  private SearchTest tester;
  
  public ScheduleTabPanel(SearchTest s) { //will take objects later
    
    //initializing everything
    tester = s;
    
    buttons = new JButton[5][5];
    timeLabel = new JLabel[16];
    weekdays = new JLabel[5];
    String[] times = {"08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
      "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00",
      "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"}; //all of the times for the calendar
    
    String[] days = {"M", "T", "W", "TH", "F"};
    
    for (int i = 0; i < 6; i++) {
      JLabel hourLabel = new JLabel(times[i]); //initializes a time label
      timeLabel[i] = hourLabel;
    }
    for (int n = 0; n < days.length; n++) {
      JLabel dayLabel = new JLabel(days[n]); //initializes a day label
      weekdays[n] = dayLabel;
    }
    
    //System.out.println(Arrays.toString(timeLabel)); testing code
    
    //beginning the arrangement of the panel
    setLayout(new FlowLayout()); //use FlowLayout to clearly separate the parts of the tabbed panel
    
    JPanel top = new JPanel(); //put what course you're searching for here
    
    searchResults = new JLabel("Search results will appear here.",
                               SwingConstants.CENTER);
    //NEED TO ADD SOME EVENT LISTENER TO THIS 
    top.add(searchResults); //adds the label to the top panel
    add(top); //adds top panel to overall ScheduleTabPanel
    
    JPanel middle = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    
    for (int j = 0; j < buttons.length; j++) {
      c.gridy = j + 1; //sets the row position of the time label
      c.gridx = 0; //gridx set to 0 so that all the time labels are in the first column
      c.insets = new Insets(3, 3, 3, 3); //inserts padding in between each time label
      middle.add(timeLabel[j], c);
      
      c.gridy = 0; //set to 0 so that all day labels are in the first row
      c.gridx = j + 1; //sets column position of the day label
      c.insets = new Insets(3, 3, 3, 3); //inserts padding between day label
      middle.add(weekdays[j], c);
      c.gridy = j + 1; //sets the row position of the button to j
      for (int k = 0; k < buttons[j].length; k++) {
        c.gridx = k + 1; //sets the column position of the button to k
        buttons[j][k] = new JButton("");
        buttons[j][k].setPreferredSize(new Dimension(50, 40)); //sets the size of the buttons
        c.insets = new Insets(-3, -3, -3, -3); //insets with negative values removes spaces in between the buttons
        middle.add(buttons[j][k], c);
        buttons[j][k].addActionListener(new ButtonListener());
      }
    }
    
    String[] arr = tester.getClass(0);
    Time start = new Time(arr[1]); 
    Time end = new Time(arr[2]);
    
    //rounds both start and end time to nearest half hour
    start.roundToNearestHalfHour(); 
    end.roundToNearestHalfHour();
    
    //gets corresponding index position for the buttons
    int indexStart = Arrays.asList(times).indexOf(start.toString());
    int indexEnd = Arrays.asList(times).indexOf(end.toString());
    
    for (int i = indexStart; i <= indexEnd; i++) {
      buttons[i][0].setText("hi");
      System.out.println("hi");
    }
    
    
//    int size = tester.getSize();
//    for (int i = 0; i < size; i++) {
//      String[] arr = tester.getClass(i);
//      Time start = new Time(arr[1]); 
//      Time end = new Time(arr[2]);
//      
//      //rounds both start and end time to nearest half hour
//      start.roundToNearestHalfHour(); 
//      end.roundToNearestHalfHour();
//      
//      //gets corresponding index position for the buttons
//      int indexStart = Arrays.asList(times).indexOf(start.toString());
//      int indexEnd = Arrays.asList(times).indexOf(end.toString());
//      
//      
//      
//    }
    
    
    add(middle);
    
  }
  private class ButtonListener implements ActionListener {
    
    public void actionPerformed(ActionEvent event) {
      for (int a = 0; a < buttons.length; a++) {
        for (int b = 0; b < buttons[a].length; b++) {
          if (buttons[a][b] == event.getSource()) {
            //something
          }
          
        }
      }
    }
  }
}