/*ScheduleTabPanel2.java
 CS 230 final project
 Modified by: ili
 Modified date: 04/22/17
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class ScheduleTabPanel2 extends JPanel {
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
  private FinalClasses finalClasses;
  
  
  public ScheduleTabPanel2(SearchTest search, FinalClasses fclasses) { //will take objects later
    //initializing everything
    tester = search;
    finalClasses = fclasses;
    option = new ImageIcon("images/blue.jpg");
    official = new ImageIcon("images/pink.png");
    int counter = 0;
    
    courses = new LinkedList<JButton>();
    courseInfo = new LinkedList<Course>();
    
    buttons = new JButton[31][5];
    timeLabel = new JLabel[31];
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
    
    //beginning the arrangement of the panel
    setLayout(new FlowLayout()); //use FlowLayout to clearly separate the parts of the tabbed panel
    
    JPanel top = new JPanel(); //put what course you're searching for here
    top.setLayout(new BoxLayout(top, BoxLayout.PAGE_AXIS));
    
    searchResults = new JLabel("Search results will appear here.",
                               SwingConstants.CENTER);
    top.add(searchResults);

    for (int m = 0; m < tester.getSize(); m++) {
      String[] dates = tester.getClass(m).getDate();
      String d = "";
      for (int a = 0; a < dates.length; a++) {
        if (dates[a] != null) {
          d += dates[a];
        }
      }
      String s = tester.getClass(m).getStartTime();
      String e = tester.getClass(m).getEndTime();
      Time start = new Time(s); 
      Time end = new Time(e);
      JButton course = new JButton();
      course.setText("<html>Course: " + tester.getClass(m).getTitle() + "<br>Time: " + s + "-" + e + "<br>Meeting times: " + 
                     d + "<br>Professor: " + tester.getClass(m).getProf());
      course.addActionListener(new ButtonListener());
      top.add(course);
      course.setActionCommand(Integer.toString(counter));
      courses.add(course);
      courseInfo.add(tester.getClass(m));
      counter++;
    }
    
    add(top); //adds top panel to overall ScheduleTabPanel
    
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
  
  private void changeButton(int index, Course c) {
    Time start = new Time(c.getStartTime());
    Time end = new Time(c.getEndTime());
    
    //rounds both start and end time to nearest half hour
    start.roundToNearestHalfHour(); 
    end.roundToNearestHalfHour();
    
    //gets corresponding index position for the buttons
    int indexStart = Arrays.asList(times).indexOf(start.toString());
    int indexEnd = Arrays.asList(times).indexOf(end.toString());
    
    for (int b = indexStart; b <= indexEnd; b++) {
      buttons[b][0].setBorder(BorderFactory.createEmptyBorder());
      buttons[b][0].setIcon(option);
      buttons[b][0].addMouseListener(new MouseHover(index));
    } 
  }
  private class ButtonListener implements ActionListener {
    
    public void actionPerformed(ActionEvent event) {
      int action = Integer.parseInt(event.getActionCommand());
      for (int c = 0; c < courses.size(); c++) {
        if (Integer.parseInt(courses.get(c).getActionCommand()) == action) {
          courses.get(c).setEnabled(false);
          changeButton(c, courseInfo.get(c));
        }
      }
      
    }
  }
  private class MouseHover implements MouseListener {
    private int i;
    public MouseHover(int index) {
      i = index;
    }
    public void mouseEntered(MouseEvent e) {
      JOptionPane.showMessageDialog(null, courses.get(i).getText());
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
  }
}