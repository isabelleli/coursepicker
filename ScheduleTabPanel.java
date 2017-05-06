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
  private ImageIcon option;
  private ImageIcon official;
  private LinkedList<String> info;
  private JFrame frame;

  
  public ScheduleTabPanel(SearchTest search) { //will take objects later
    //initializing everything
    tester = search;
    option = new ImageIcon("images/blue.jpg");
    official = new ImageIcon("images/pink.png");
    int counter = 0;
    
    buttons = new JButton[5][5];
    timeLabel = new JLabel[16];
    weekdays = new JLabel[5];
    String[] times = {"08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
      "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00",
      "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"}; //all of the times for the calendar
    
    String[] days = {"M", "T", "W", "TH", "F"};
    
    info = new LinkedList<String>();
    
    for (int i = 0; i < 7; i++) {
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
    

    for (int a = 0; a < tester.getSize(); a++) {
      String[] dates = tester.getClass(a).getDate();
      String d = "";
      for (int i = 0; i < dates.length; i++) {
        if (dates[i] != null) {
          d += dates[i];
        }
      }
      String s = tester.getClass(a).getStartTime();
      String e = tester.getClass(a).getEndTime();
      Time start = new Time(s); 
      Time end = new Time(e);
      
      //rounds both start and end time to nearest half hour
      start.roundToNearestHalfHour(); 
      end.roundToNearestHalfHour();
      
      //gets corresponding index position for the buttons
      int indexStart = Arrays.asList(times).indexOf(start.toString());
      int indexEnd = Arrays.asList(times).indexOf(end.toString());
      
      for (int b = indexStart; b <= indexEnd; b++) {
        buttons[b][0].setBorder(BorderFactory.createEmptyBorder());
        buttons[b][0].setIcon(option);
      }
      buttons[indexStart][0].setActionCommand(Integer.toString(counter));
      for (int f = indexStart + 1; f <= indexEnd; f++) {
        buttons[f][0].setEnabled(false);
      }
      info.add("<html>Course: " + tester.getClass(a).getTitle() + "<br>Time: " + s + "-" + e + "<br>Meeting times: " + 
               d + "<br>Professor: " + tester.getClass(a).getProf());
      counter++;
      
    }
    
    
    add(middle);
    
  }
  private class ButtonListener implements ActionListener {
    
    public void actionPerformed(ActionEvent event) {
      int action = Integer.parseInt(event.getActionCommand());
      for (int a = 0; a < buttons.length; a++) {
        for (int b = 0; b < buttons[a].length; b++) {
          if (Integer.parseInt(buttons[a][b].getActionCommand()) == action) {
            int n = JOptionPane.showConfirmDialog(buttons[a][b],info.get(action), "Hi", JOptionPane.YES_NO_OPTION);
            if (n == 0) {
              buttons[a][b].setIcon(official);
              //add to finalClasses linkedlist
            }
            info.remove(action);
            buttons[a][b].setEnabled(false);
            
          }
          
        }
      }
    }
  }
}