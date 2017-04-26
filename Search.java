/* Search.java
<<<<<<< HEAD
 * Jennifer Wang, Isabelle Li, Shan Lu
 * CS 230 Final Project
 * Object to store results from a single search
 */

import java.util.*;

public class Search{
  //instance variables
  private LinkedList<Course> searchResults;
  private CourseInformationTest courseInfo;
  private LinkedList<String> crns;
  
  public Search(CourseInformationTest test){
    searchResults = new LinkedList<Course>(); 
    courseInfo = test;
    crns = new LinkedList<String>();
  }
  
  public void searchCourse(String title) {
    crns = courseInfo.searchCRNs(title);
    
    for (int i = 0; i < crns.size(); i++){
      String[] details = courseInfo.searchDetails(crns.get(i));
      Course newClass = new Course(crns.get(i), details);
      searchResults.add(newClass); 
    }
  }

  public LinkedList<Course> getSearchResults(){
    return searchResults;
  }
  
  public static void main(String[] args) {
    CourseInformationTest test = new CourseInformationTest();
    
    Search bob = new Search(test);
    bob.searchCourse("CS 111");
    System.out.println(bob.getSearchResults());
  }
}
