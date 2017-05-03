/* SearchTabPanel.java
 * Jennifer Wang, Isabelle Li, Shan Lu
 * CS 230 Final Project
 * This is the panel that will show the search interface and limited results in the GUI
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SearchTabPanel extends JPanel{
   //instance variables
  private JButton searchButton;
  private JTextField courseName;
  private JLabel results;
  
  public SearchTabPanel(Search userSearch){
    
    //a course will not have more than 5 sections and 5 pieces of information
    //searchResults = new String[5][5]; put in Search.java, printed here?
     
    //create Panel and its components
    setLayout(new BorderLayout());
    setBackground(Color.pink);
    add(new JLabel("Please enter the title of the course you wish to take and click search. Example: CS 111"), BorderLayout.NORTH);
    add(new JLabel("Course Title: "), BorderLayout.WEST);  
    results = new JLabel ("Information about the course you have searched for will appear here.");
    add(results, BorderLayout.SOUTH);
    
    searchButton = new JButton("Search");
    ButtonListener b = new ButtonListener(userSearch);
    searchButton.addActionListener(b);
    add(searchButton, BorderLayout.EAST);
    
    courseName = new JTextField();
    add(courseName, BorderLayout.CENTER); 
  }
  
  private class ButtonListener implements ActionListener{
    //CourseInformationTest object, containing hash tables, is an input
    private Search userSearch;
    
    public ButtonListener(Search s){
      //passes Search object into ButtonListener and calls the super() constructor
      super();
      userSearch = s;
    }
    
    public void actionPerformed(ActionEvent event) {
      //creates new search whenever search button is pressed, adds new info to searchResults
      if (event.getSource() == searchButton) {
        String name = courseName.getText();
        userSearch.searchCourse(name);
        results.setText(userSearch.getSearchResults().toString());     
      }
    }
  }
  


}