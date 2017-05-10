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
    searchResults = new LinkedList<Course>(); 
    courseInfo = info;
    crns = new LinkedList<String>();
  }
  
  public void searchCourse(String title) throws IllegalArgumentException{
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
    String s = "<html><body>";
    for (int i = 0; i < searchResults.size(); i++) {
      s += searchResults.get(i).toString() + "<br>";
    }
    return s + "</body></html>"; 
  }
  
  public Course getCourse(int i) {
    return searchResults.get(i);
  }
  
  public int getSize() {
    return searchResults.size();
  }
  
  public static void main(String[] args) {
    CourseInformation test = new CourseInformation("CourseInfo.txt");
    
    Search bob = new Search(test);
    bob.searchCourse("CS 111");
    System.out.println(bob.getSearchResults());
  }
}
