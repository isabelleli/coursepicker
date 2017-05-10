/* Search.java
 * Jennifer Wang, Isabelle Li, Shan Lu
 * CS 230 Final Project
 * Object to store results from a single search
 * Modified by: jwang17
 * Modified date: 05/07/17
 */

import java.util.*;

public class Search{
  //instance variables
  private LinkedList<Course> searchResults;
  private CourseInformation courseInfo;
  private LinkedList<String> crns;
  
  public Search(CourseInformation info){
    //creates new empty linked lists of Course objects and crns, takes in CourseInformation object w/ 2 hash tables 
    searchResults = new LinkedList<Course>(); 
    courseInfo = info;
    crns = new LinkedList<String>();
  }
  
  public void searchCourse(String title) throws IllegalArgumentException{
    /* when given a course title, searches the first hash table to get the linked list of all crns with course title,
     * then loops through the array of crns to get the details of each section with said course title. If inputted
     * title is not in the hash table, throws an IllegalArgumentException*/
    if (courseInfo.getFirstTable().containsKey(title)){
      searchResults.clear(); 
      crns = courseInfo.searchCRNs(title);
      for (int i = 0; i < crns.size(); i++){
        String crn = crns.get(i);
        String[] details = courseInfo.searchDetails(crn);
        Course newClass = new Course(crn, details);
        searchResults.add(newClass); 
      }
    } else { 
      throw new IllegalArgumentException();
    }
  }

  public String getSearchResults(){
    //formatted using html to display in GUI
    String s = "<html><body>";
    for (int i = 0; i < searchResults.size(); i++) {
      s += searchResults.get(i).toString() + "<br>";
    }
    return s + "</body></html>"; 
  }
  
  public Course getCourse(int i) {
    //gets the course at index i in searchResults
    return searchResults.get(i);
  }
  
  public int getSize() {
    //returns size of searchResults
    return searchResults.size();
  }
  
  public static void main(String[] args) {
    //initial testing
    CourseInformation test = new CourseInformation("CourseInfo.txt");
    
    Search bob = new Search(test);
    bob.searchCourse("CS 111");
    System.out.println(bob.getSearchResults());
  }
}
