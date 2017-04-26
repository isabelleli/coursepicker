/* CourseInformationTest.java
 * Jennifer Wang, Isabelle Li, Shan Lu
 * CS 230 Final Project
 * Testing CourseInformation object
 */

public class CourseInformationTest{
  //instance variables
  private Hashtable<String, String[]> courseCRN;
  private Hashtable<String, String[]> courseDetails;

  // CONSTRUCTOR
  public CourseInformationTest(){
    courseCRN = new Hashtable<String, String[3]>();
    courseDetails = new Hashtable<String, String[8]>();
    
    courseCRN.put("CS 111", ["10006", "10042", "12822"]);
    courseDetails.put("10006", ["TF", "11:00AM", "ENI", "01", "9:50AM", "CS", "CS 111"]);
    courseDetails.put("10042", ["TF", "12:20AM", "LYN", "02", "9:50AM", "CS", "CS 111"])
     
    }
  }
  
  public Hashtable<String, String[]> searchDetails(String crn) { //or write getDetails?
    return courseDetails.get(crn);
  }
  
  public Hashtable<String, String[]> searchCRNs(String title) { 
    return courseDetails.get(title);
  }



}