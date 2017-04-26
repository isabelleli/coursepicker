/* ScheduleGeneratorGUI.java
 * Jennifer Wang, Isabelle Li, Shan Lu
 * CS 230 Final Project
 * This is the GUI that will build the framework to show both panels
 */

import javax.swing.JFrame;
import javax.swing.*;

public class ScheduleGeneratorGUI {

  public static void main (String[] args) {
    // creates and shows a Frame 
    JFrame frame = new JFrame("Schedule Generator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // creates the objects we need to use
    CourseInformation info = new CourseInformation();
    Search userSearch = new Search(info);
    FinalClasses classList = new FinalClasses();

    // Creates tab pane and adds different tabs to it.
    JTabbedPane tp = new JTabbedPane();
    tp.addTab("Search", new SearchTabPanel(userSearch));
    tp.addTab("Schedule Generator", new ScheduleTabPanel(userSearch, classList)); 
    
    // Makes sure the user can actually see things
    frame.getContentPane().add(tp);
    
    frame.pack();
    frame.setVisible(true);
  }



}