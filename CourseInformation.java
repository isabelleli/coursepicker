/*
 CourseInformation.java
 2 Hash Tables storing all course information
 Written by: Shan Lu
 Apr. 26, 2017
*/


import java.util.*;
import java.net.*;
import java.io.*;

public class CourseInformation {
  
  String[] crn = new String[750]; // an array of CRNs
  String[] search = new String[750]; // an array of "subject + number" eg. "CS 111"
  String number;
  String[] line;
  Hashtable<String, LinkedList<String>> courseCRN; 
       // the first hashtable: search = a linkedlist of CRNs
       // eg. "PHYS 106=[11251, 11252, 11323, 11331]"
  
  /*
   Constructor
   reads courses info from imput file and puts them into hashtable courseCRN
  */
  public CourseInformation(String fileName) {
    
    courseCRN = new Hashtable<String, LinkedList<String>>(1000);
    int count = 0;
    
    try {
      
      FileReader fileReader = new FileReader(fileName);
      
      BufferedReader bufferedReader = new BufferedReader(fileReader);
 
      while (bufferedReader.ready()) { // while has more to read
        
        String[] line = bufferedReader.readLine().trim().split(":");
        if (line[0].equals("\"CRN\"")) {
          crn[count] = line[1].trim().substring(1,line[1].length()-3);
            // gets rid of spaces and "", puts into the array for CRNs
        }
        
        else if (line[0].equals("\"Number\"")) {
          number = line[1].trim().substring(1,line[1].length()-3);
             // gets rid of spaces and "", stores into a string
        }
        
        else if (line[0].equals("\"Subject\"")) {
          search[count] = line[1].trim().substring(1,line[1].length()-2) + " " + number;
          count++; // increments count after every course
        }  

      }
      
      // gets info from two arrays and puts in to hashtable courseCRN
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
   main method
   testing
  */
  public static void main (String[] args) {
    CourseInformation a = new CourseInformation("CourseInfo.txt");
    System.out.println(a.getFirstTable());
    System.out.println(a.getFirstTable().get("CS 111"));
  } 
}