/* CoursePickerPanel2.java
 * CS 230 final project
 * Modified by: ili
 * Modified date: 05/06/17
 * ili, jwang17, slu5
 */

//have to do oNE BIG CLASS 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class CoursePickerPanel2 extends JPanel{
  private JTabbedPane tp;
  private Search searchObj;
  private FinalClasses fClasses;
  private JButton searchButton;
  private JTextField courseName;
  //private JLabel results;
  
  private JLabel searchResults; //text that displays course search results of user
  private JPanel buttonResults;
  private JPanel searchTab;
  private JButton[][] buttons; 
  private JLabel[] timeLabel; 
  private JLabel[] weekdays; 
  private String[] days; 
  private String[] times; 
  private LinkedList<JButton> courses;  
  private SearchTest tester;
  private ImageIcon official;
  private FinalClasses finalClasses; 
  private int counter;
  
  
  public CoursePickerPanel2(Search sObj, FinalClasses f) {
    tp = new JTabbedPane();
    
    searchObj = sObj;
    finalClasses = f;
    official = new ImageIcon("Images/red.jpg");
    counter = 0; //used with setActionCommand to give each course button a unique ID
    
    searchTab = new JPanel();
    searchTab.setLayout(new BoxLayout(searchTab, BoxLayout.PAGE_AXIS));
    JPanel searchFunction = new JPanel(new BorderLayout());
    searchFunction.setMaximumSize(new Dimension(500, 200));
    searchFunction.setBackground(Color.pink);
    searchFunction.add(new JLabel("<html>Please enter the title of the course you wish to take and click search.<br>Example: CS 111</html>"), BorderLayout.NORTH);
    searchFunction.add(new JLabel("Course Title: "), BorderLayout.WEST);  
    
    searchButton = new JButton("Search");
    searchButton.addActionListener(new ButtonListener(searchObj));
    searchFunction.add(searchButton, BorderLayout.EAST);
    
    courseName = new JTextField();
    searchFunction.add(courseName, BorderLayout.CENTER); 
    
    searchResults = new JLabel("Buttons will appear where you can add your course.",
                               SwingConstants.CENTER);
    searchFunction.add(searchResults, BorderLayout.SOUTH);
    
    searchTab.add(searchFunction);
    tp.add("Search", searchTab);
    
//    setLayout(new FlowLayout());
//    
//    leftSide = new JPanel();
//    leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.PAGE_AXIS));
    
    JPanel schedule = new JPanel(new FlowLayout());
    
    buttonResults = new JPanel();
    buttonResults.setLayout(new WrapLayout());
    
    courses = new LinkedList<JButton>(); //stores all of the buttons representing the classes from the Search object
    
    buttons = new JButton[31][5]; //represents the calendar 
    
    //timeLabel array populated by all of the times ranging from 8:30 to 23:30
    timeLabel = new JLabel[31];
    times = new String[] {"08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
      "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00",
      "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30"}; //all of the times for the calendar
    for (int a = 0; a < timeLabel.length; a++) {
      JLabel hourLabel = new JLabel(times[a]); //initializes a time label
      timeLabel[a] = hourLabel;
    }
    
    //weekDays array populated by all of the days of a weekday
    weekdays = new JLabel[5];
    days = new String[]{"M", "T", "W", "TH", "F"};
    for (int b = 0; b < days.length; b++) {
      JLabel dayLabel = new JLabel(days[b]); //initializes a day label
      weekdays[b] = dayLabel;
    }
    
    JPanel calendar = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    
    for (int i = 0; i < buttons.length; i++) {
      c.gridy = i + 1; //sets the row position of the time label
      c.gridx = 0; //gridx set to 0 so that all the time labels are in the first column
      c.insets = new Insets(2, 2, 2, 2); //inserts padding in between each time label
      calendar.add(timeLabel[i], c); //adds the time labels to the side of the calendar
      for (int j = 0; j < buttons[i].length; j++) {
        c.gridx = j + 1; //sets the column position of the button to k
        buttons[i][j] = new JButton("");
        buttons[i][j].setPreferredSize(new Dimension(90, 20)); //sets the size of the buttons
        c.insets = new Insets(-2, -2, -2, -2); //insets with negative values removes spaces in between the buttons
        calendar.add(buttons[i][j], c);
        buttons[i][j].setEnabled(false); //prevents user from clicking any buttons on the calendar
      }
    }
    for (int k = 0; k < weekdays.length; k++) { //adds the weekday labels on the results of the calendar
      c.gridy = 0; //set to 0 so that all day labels are in the first row
      c.gridx = k + 1; //sets column position of the day label
      c.insets = new Insets(3, 3, 3, 3); //inserts padding between day label
      calendar.add(weekdays[k], c);
      c.gridy = k + 1; //sets the row position of the button to j
    }
    
    schedule.add(calendar); 
    tp.add("Schedule", schedule);
    add(tp);
  }
  
  private void setSearchResults() {
    buttonResults.removeAll();
    courses.clear();
    
    for (int g = 0; g < searchObj.getSize(); g++) {
      String[] dates = searchObj.getCourse(g).getDate(); //gets the String array of dates that the class occurs
      String d = ""; 
      //stores all of the days within dates array into a string in order to display on GUI
      for (int f = 0; f < dates.length; f++) {
        if (dates[f] != null) {
          d += dates[f];
        }
      }
      
      String s = searchObj.getCourse(g).getStartTime(); 
      String e = searchObj.getCourse(g).getEndTime();
      
      //creates new button to be displayed next to calendar; user would click this to add course to their calendar
      JButton course = new JButton();
      course.setText("<html> CRN: " + searchObj.getCourse(g).getCRN() + "<br>Course: " + searchObj.getCourse(g).getTitle() + "<br>Meeting times: " + d + 
                     "<br>Time: " + s + "-" + e + "<br>Professor: " + searchObj.getCourse(g).getProf() + 
                     "<br>Section: " + searchObj.getCourse(g).getSection());
      course.setFont(new Font("Calibri", Font.PLAIN, 11));
      course.addActionListener(new ButtonListener(searchObj));
      course.setActionCommand(Integer.toString(counter)); //ActionCommand represents a unique idenfication for the button
      courses.add(course); //adds button to the LinkedList of course buttons
      counter++;
      course.setPreferredSize(new Dimension(230, 90));
      buttonResults.add(course);
    }
    searchTab.add(buttonResults);
    
  }
  
  
  private void addToCalendar(int index, Course c) {
    String[] dates = c.getDate();
    Time start = new Time(c.getStartTime());
    Time end = new Time(c.getEndTime());
    
    //rounds both start and end time to nearest half hour
    start.roundToNearestHalfHour(); 
    end.roundToNearestHalfHour();
    
    //gets corresponding index position for the buttons
    int indexStart = Arrays.asList(times).indexOf(start.toString());
    int indexEnd = Arrays.asList(times).indexOf(end.toString());
    System.out.println(indexStart);
    System.out.println(indexEnd);
    //shades the places where the class would occur on the calendar
    for (int m = 0; m < dates.length; m++) {
      if (dates[m] != null) {
        int dayPos = Arrays.asList(days).indexOf(dates[m]);
        
        for (int n = indexStart; n <= indexEnd; n++) {
          buttons[n][dayPos].setBorder(BorderFactory.createEmptyBorder());
          buttons[n][dayPos].setIcon(official);
          buttons[n][dayPos].addMouseListener(new MouseHover(index));
        }
      }
    }
  }
  
  private void removeFromCalendar(Course c) {
    String[] dates = c.getDate();
    Time start = new Time(c.getStartTime());
    Time end = new Time(c.getEndTime());
    
    //rounds both start and end time to nearest half hour
    start.roundToNearestHalfHour(); 
    end.roundToNearestHalfHour();
    
    //gets corresponding index position for the buttons
    int indexStart = Arrays.asList(times).indexOf(start.toString());
    int indexEnd = Arrays.asList(times).indexOf(end.toString());
    System.out.println(indexStart);
    System.out.println(indexEnd);
    //shades the places where the class would occur on the calendar
    for (int m = 0; m < dates.length; m++) {
       int dayPos = Arrays.asList(days).indexOf(dates[m]);
       for (int n = indexStart; n <= indexEnd; n++) {
          buttons[n][dayPos].setBorder(BorderFactory.createEmptyBorder());
          buttons[n][dayPos].setIcon(null);
          MouseListener[] mouseListeners = buttons[n][dayPos].getMouseListeners();
          for (MouseListener mouseListener : mouseListeners) {
            buttons[n][dayPos].removeMouseListener(mouseListener);
          }
       }
    }
  }
 
  private int getCourseIndex(String text) {
    String crn = text.substring(12,17);
    System.out.println(crn);
    for (int i = 0; i < finalClasses.getSize(); i++) {
      if (crn.equals(finalClasses.getClass(i).getCRN())) {
        return i;
      }
    }
    return -1;
  }
  
  private class ButtonListener implements ActionListener{
    //CourseInformationTest object, containing hash tables, is an input
    private Search searchObj;
    
    public ButtonListener(Search s){
      //passes Search object into ButtonListener and calls the super() constructor
      super();
      searchObj = s;
    }
    
    public void actionPerformed(ActionEvent event) {
      //creates new search whenever search button is pressed, adds new info to searchResults
      if (event.getSource() == searchButton) {
        try {
          String name = courseName.getText().toUpperCase();
          searchObj.searchCourse(name);
          setSearchResults();
          //results.setText(searchObj.getSearchResults().toString());  
        } catch (IllegalArgumentException e){
          System.out.println("blah");
          //results.setText("Invalid course name. Please search again.");
        }
      }
      else {
        int action = Integer.parseInt(event.getActionCommand()); //gets the int representation of the ActionCommand
        for (int n = 0; n < courses.size(); n++) { 
          //searches through the LinkedList of buttons to find a matching actionCommand
          if (Integer.parseInt(courses.get(n).getActionCommand()) == action) {
            //courses.get(n).setEnabled(false);
            finalClasses.addClass(searchObj.getCourse(n));
            tp.setSelectedIndex(1);
            addToCalendar(n, searchObj.getCourse(n));
          }
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
      Object[] options = { "OK", "REMOVE" };
      int response = JOptionPane.showOptionDialog(null, courses.get(i).getText(), "Course Selection", 
                                   JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
      if (response == 1) {
         int index = getCourseIndex(courses.get(i).getText());
         //removeFromCalendar(finalClasses.getClass(index));
         System.out.println("before: " + finalClasses);
         finalClasses.removeClass(index);
         System.out.println("after: " + finalClasses);
      }
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


