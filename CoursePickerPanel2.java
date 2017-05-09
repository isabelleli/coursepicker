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
  private FinalClasses finalClasses;
  
  //private instance variables to create first tab
  private JPanel searchTab; //contains all of the elements to search a course
  private JButton searchButton;
  private JTextField courseName;
  private JLabel searchResults;
  
  private JPanel buttonResults; //shows the grid of buttons representing courses
  private LinkedList<JButton> courses;  
  private JButton[][] buttons; //all of the buttons for the calendar
  private JLabel[] timeLabel; 
  private String[] times; 
  private JLabel[] weekdays; 
  private String[] days; 
  private ImageIcon official;
  private int counter;
  private boolean exist;
  
  
  public CoursePickerPanel2(Search sObj, FinalClasses f) {
    tp = new JTabbedPane();
    searchObj = sObj;
    finalClasses = f;
    
    //creates the searching part of first tab
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
    
    //sets up the grid of buttons of classes to add 
    buttonResults = new JPanel();
    buttonResults.setLayout(new WrapLayout()); //WrapLayout taken from a blog; wraps the buttons 
    
    
    //creating second tab
    official = new ImageIcon("Images/red.jpg"); //doesn't work for some reason??
    counter = 0; //used with setActionCommand to give each course button a unique ID
    
    JPanel schedule = new JPanel(new FlowLayout()); //represents panel of the second tab
    
    
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
    
    JPanel calendar = new JPanel(new GridBagLayout()); //stores all of the buttons that make up the calendar
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
    buttonResults.removeAll(); //clears the JPanel of the first tab that contains the grid of courses
    courses.clear(); //removes all of the previous buttons for the courses
    
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
      course.setText("<html> CRN: " + searchObj.getCourse(g).getCRN() + "<br>Course: " + 
                     searchObj.getCourse(g).getTitle() + "<br>Meeting times: " + d + "<br>Time: " + s + "-" + e + 
                     "<br>Professor: " + searchObj.getCourse(g).getProf() + "<br>Section: " + 
                     searchObj.getCourse(g).getSection());
      course.setFont(new Font("Calibri", Font.PLAIN, 11));
      course.addActionListener(new ButtonListener(searchObj));
      course.setActionCommand(Integer.toString(counter)); //ActionCommand represents a unique idenfication for the button
      courses.add(course); //adds button to the LinkedList of course buttons
      counter++;
      course.setPreferredSize(new Dimension(230, 105));
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
    //shades the places where the class would occur on the calendar
    for (int m = 0; m < dates.length; m++) {
      if (dates[m] != null) {
        int dayPos = Arrays.asList(days).indexOf(dates[m]); //gets the index corresponding to the day of the week
        for (int n = indexStart; n <= indexEnd; n++) {
          if (!buttons[n][dayPos].getText().equals("")) { 
            exist = true;
          }
        }
      }
    }
    if (!exist) {
      for (int m = 0; m < dates.length; m++) {
        if (dates[m] != null) {
          int dayPos = Arrays.asList(days).indexOf(dates[m]); //gets the index corresponding to the day of the week
          for (int n = indexStart; n <= indexEnd; n++) {
          buttons[n][dayPos].setText(c.getCRN());
          //buttons[n][dayPos].setIcon(official);
          buttons[n][dayPos].addMouseListener(new MouseClicker(index));
          }
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
    
    //unshades the places where the class would occur on the calendar
    for (int m = 0; m < dates.length; m++) {
      if (dates[m] != null) {
        int dayPos = Arrays.asList(days).indexOf(dates[m]); //gets the index corresponding to the day of the week
        for (int n = indexStart; n <= indexEnd; n++) {
          //buttons[n][dayPos].setIcon(null);
          buttons[n][dayPos].setText("");
          //removes the mouselistener from the buttons corresponding to the class being removed
          MouseListener[] mouseListeners = buttons[n][dayPos].getMouseListeners();
          for (MouseListener mouseListener : mouseListeners) {
            buttons[n][dayPos].removeMouseListener(mouseListener);
          }
        }
      }
    }
  }
  
  /* Helper method that searches through finalClasses
   * and removes the class with the same crn as the text inputed
   */
  private int getCourseIndex(String crn) {
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
          searchResults.setText("Below are all the available sections.");
        } catch (IllegalArgumentException e){
          searchResults.setText("Invalid course name. Please search again.");
        }
      }
      else {
        int action = Integer.parseInt(event.getActionCommand()); //gets the int representation of the ActionCommand
        for (int n = 0; n < courses.size(); n++) { 
          //searches through the LinkedList of buttons to find a matching actionCommand
          if (Integer.parseInt(courses.get(n).getActionCommand()) == action) {
            //courses.get(n).setEnabled(false);
            addToCalendar(n, searchObj.getCourse(n));
            if (!exist) {
            finalClasses.addClass(searchObj.getCourse(n));
            tp.setSelectedIndex(1);
            }
            else {
              searchResults.setText("Time conflict. Please choose another section.");
              exist = false;
            }
          }
        }
        
      }
    }
  }
  private class MouseClicker implements MouseListener {
    private int i;
    public MouseClicker(int index) {
      i = index;
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseClicked(MouseEvent e) {
      Object[] options = { "OK", "REMOVE" };
      String crn = ((JButton)e.getSource()).getText(); //crn of the course corresponding to said JButton
      int index = getCourseIndex(crn); //index of the course in finalClasses
      int response = JOptionPane.showOptionDialog(null, finalClasses.getClass(index).toString(), "Course Selection", 
                                                  JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
      if (response == 1) {
        removeFromCalendar(finalClasses.getClass(index));
        finalClasses.removeClass(index);
      }
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
  }
}

// cannot remove courses added in last round
