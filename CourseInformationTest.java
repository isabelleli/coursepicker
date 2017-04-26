/* CourseInformationTest.java
 * Jennifer Wang, Isabelle Li, Shan Lu
 * CS 230 Final Project
 * Testing CourseInformation object
 */

import java.util.*;

public class CourseInformationTest{
  //instance variables
  private Hashtable<String, LinkedList<String>> courseCRN;
  private Hashtable<String, String[]> courseDetails;

  // CONSTRUCTOR
  public CourseInformationTest(){
    courseCRN = new Hashtable<String, LinkedList<String>>();
    courseDetails = new Hashtable<String, String[]>();
    
    courseCRN.put("CS 111", new LinkedList<String>(Arrays.asList("10006", "10042", "12822")));
    courseDetails.put("10006", new String[] {"TF", "11:00AM", "ENI", "01", "09:50AM", "CS", "CS 111"});
    courseDetails.put("10042", new String[] {"MTHF", "12:20AM", "LYN", "02", "11:00AM", "CS", "CS 111"});
     
    }
  
  public String[] searchDetails(String crn) { 
    return courseDetails.get(crn);
  }
  
  public LinkedList<String> searchCRNs(String title) { 
    return courseCRN.get(title);
  }
  
  public static void main(String[] args) {
    CourseInformationTest test = new CourseInformationTest();
    System.out.println(test.searchCRNs("CS 111"));
    System.out.println(Arrays.toString(test.searchDetails("10006")));
    System.out.println(Arrays.toString(test.searchDetails("10042")));
  
  }
}