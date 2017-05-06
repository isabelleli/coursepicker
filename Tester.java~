/*Tester.java (trying to figure out what i have an error!!!)
 CS 230 final project
 Modified by: ili
 Modified date: 04/22/17
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class Tester extends JPanel {
  private JLabel searchResults; //text that displays course search results of user
  private JButton[][] buttons;
  private JLabel[] timeLabel;
  private JLabel[] weekdays;
  private String[] times;
  private LinkedList<JButton> courses;
  private LinkedList<Course> courseInfo;
  private SearchTest tester;
  private ImageIcon option;
  private ImageIcon official;
  private JFrame frame;
  
  
  public Tester(SearchTest search) { //will take objects later
    //initializing everything
    tester = search;
    option = new ImageIcon("images/blue.jpg");
    official = new ImageIcon("images/pink.png");
    int counter = 0;
    
    buttons = new JButton[31][5];
    timeLabel = new JLabel[16];
    weekdays = new JLabel[5];
    times = new String[] {"08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
      "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00",
      "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"}; //all of the times for the calendar
    
    String[] days = {"M", "T", "W", "TH", "F"};
    
    for (int i = 0; i < timeLabel.length; i++) {
      JLabel hourLabel = new JLabel(times[i]); //initializes a time label
      timeLabel[i] = hourLabel;
    }
    for (int n = 0; n < days.length; n++) {
      JLabel dayLabel = new JLabel(days[n]); //initializes a day label
      weekdays[n] = dayLabel;
    }

    
    JPanel middle = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    
    for (int j = 0; j < buttons.length; j++) {
      c.gridy = j + 1; //sets the row position of the time label
      c.gridx = 0; //gridx set to 0 so that all the time labels are in the first column
      c.insets = new Insets(3, 3, 3, 3); //inserts padding in between each time label
      middle.add(timeLabel[j], c);
      for (int k = 0; k < buttons[j].length; k++) {
        c.gridx = k + 1; //sets the column position of the button to k
        buttons[j][k] = new JButton("");
        buttons[j][k].setPreferredSize(new Dimension(50, 40)); //sets the size of the buttons
        c.insets = new Insets(-3, -3, -3, -3); //insets with negative values removes spaces in between the buttons
        middle.add(buttons[j][k], c);
        buttons[j][k].setEnabled(false);
      }
    }
    for (int m = 0; m < weekdays.length; m++) {
      c.gridy = 0; //set to 0 so that all day labels are in the first row
      c.gridx = m + 1; //sets column position of the day label
      c.insets = new Insets(3, 3, 3, 3); //inserts padding between day label
      middle.add(weekdays[m], c);
      c.gridy = m + 1; //sets the row position of the button to j
    }
    
    add(middle);
    
  }
  
}