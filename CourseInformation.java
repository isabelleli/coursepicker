/* CourseInformation.java
 * Jennifer Wang, Isabelle Li, Shan Lu
 * CS 230 Final Project
 * 2 Hash Tables storing all course information
 * Modified by: slu5
 * Modified date: 04/26/17
 */


import java.util.*;
import java.net.*;
import java.io.*;

public class CourseInformation {
  
  int NUM = 750;
  String[] crn = new String[NUM]; // an array of CRNs
  String[] search = new String[NUM]; // an array of "subject + number" eg. "CS 111"
  String number;
  String[] line;
  String[][] details = new String[NUM][5];
  Hashtable<String, LinkedList<String>> courseCRN; 
       // the first hashtable: searchKey = a linkedlist of CRNs
       // eg. "PHYS 106 = [11251, 11252, 11323, 11331]"
  Hashtable<String, String[]> courseDetails;
       // the second hashtable: CRN = an array of the course details
       // eg. "10062 = [CS 230, Data Structures, 01, PANAGIOTIS METAXAS, TF - 09:50 AM - 11:00 AM]"
  
  /*
   Constructor
   reads courses info from input file and puts them into 
   hashtables courseCRN and courseDetails
  */
  public CourseInformation(String fileName) {
    
    courseCRN = new Hashtable<String, LinkedList<String>>(1000);
    courseDetails = new Hashtable<String, String[]>(1125);
    int count = 0;
    
    try {
      
      FileReader fileReader = new FileReader(fileName);
      
      BufferedReader bufferedReader = new BufferedReader(fileReader);
 
      while (bufferedReader.ready()) { // while has more to read
        
        String line = bufferedReader.readLine().trim();
        int colon = line.indexOf(":");
        
        if (colon != -1) { // if line has info (is not "{" or "}")
          String k = line.substring(1,colon-1); // checker
          String v = line.substring(colon+3,line.length()-2); 
                // courseInfo
                // gets rid of quotation marks, stores into a string
        
          if (k.equals("CRN")) {
            crn[count] = v;
            //crn[count] = line[1].trim().substring(1,line[1].length()-3);
              // gets rid of spaces and "", puts into the array for CRNs
          }
        
          else if (k.equals("Number")) {
            number = v;
          }
        
          else if (k.equals("Subject")) {
            String searchKey = v + " " + number;
            search[count] = searchKey;
            details[count][0]= searchKey;
          }
        
          else if (k.equals("Name")) {
            details[count][1]= v;
          }
          
          else if (k.equals("Session")) {
            details[count][2]= v;
          }
          
          else if (k.equals("Professor")) {
            details[count][3]= v;
          }
          
          else if (k.equals("Time")) {
            v = line.substring(colon+3,line.length()-1);
              // update v because "Time" is the last line, has different form in file
            details[count][4]= v;
            count++; 
              // increments count after every course
              // ("Time" is the last piece of info for a course)
          }
          
//          else if (k.equals("Start Time")) {
//            details[count][5]= v;
//          }
          
//          else if (k.equals("End Time")) {
//            details[count][6]= v;
//          }
        }
      }

      
      // gets info from two arrays and puts into hashtable "courseCRN"
      for (int i=0;i<count;i++) {
        if (!courseCRN.containsKey(search[i])) {
          LinkedList<String> value = new LinkedList<String>(); // new linkedlist
          value.add(crn[i]); // adds current CRN
          courseCRN.put(search[i],value);
        }
        else {
          LinkedList<String> list = courseCRN.get(search[i]); // current linkedlist
          list.add(crn[i]);
          courseCRN.put(search[i],list);
        }
      }
      
      // gets info from array "CRN" and 2D array "details"
      // and puts them into hashtable "courseDetails"
      for (int j=0;j<count;j++) {
        courseDetails.put(crn[j],details[j]);
      }
    }
    catch (IOException ex) { // when file not existed
      System.out.println(ex);
    }
  }
    
  
  /*
   getter method
   @return first hashtable courseCRN
  */
  public Hashtable<String, LinkedList<String>> getFirstTable() {
    return courseCRN;
  }
  
  /*
   getter method
   @return second hashtable courseDetails
  */
  public Hashtable<String, String[]> getSecondTable() {
    return courseDetails;
  }
  
  /*searching
   */
   public String[] searchDetails(String crn) { 
    return courseDetails.get(crn);
  }
  
  public LinkedList<String> searchCRNs(String title) { 
    return courseCRN.get(title);
  }
  
  /*
   main method
   testing
  */
  public static void main (String[] args) {
    CourseInformation a = new CourseInformation("CourseInfo.txt");
    System.out.println("Searching in the first hash table course - CRNs");
    System.out.println("All CRNs for \"PHYS 106\": ");
    System.out.println(a.getFirstTable().get("PHYS 106"));
    System.out.println();
    System.out.println("Searching in the second hash table CRN - details");
    System.out.println("Course information for \"14191\": ");
    System.out.println(Arrays.toString(a.getSecondTable().get("14191")));
        // print the array value as a string
    //System.out.println(a.getSecondTable().get("14191")[1]);
        // get the course name
  } 
}