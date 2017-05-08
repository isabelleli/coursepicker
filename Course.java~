/* Course.java
 * Jennifer Wang, Isabelle Li, Shan Lu
 * CS 230 Final Project
 * Object to store information about an individual course
 */

import java.util.*;
public class Course{
  //instance variables
  private String crn;
  private String time;
  private String professor;
  private String section;
  private String name;
  private String title;
  private String endTime;
  private String startTime;
  private String date;
  
  public Course(String c, String[] info) { //second parameter is CourseInformation info;
    crn = c;
    String[] infoArray = info;
    //String[] infoArray = info.searchDetails(c); //questionable? may need to copy entire array instead of just reading off
    title = infoArray[0];
    name = infoArray[1]; 
    section = infoArray[2];
    professor = infoArray[3];
    time = infoArray[4];
    
    time = time.replaceAll("\\s","");
    String [] times = time.split("-");
    date = times[0];
    startTime = times[1];
    endTime = times[2];
    
    
  }
  
  public String getCRN(){
    return crn;
  }
  
  public String[] getDate(){
    boolean thursday = false; //a way to determine whether "TH" needs to be added to the array at the end
    if (date.contains("TH")) {
      date = date.replace("TH", ""); //takes out TH from date
      thursday = true; 
    }
    
    String[] dates = new String[5];
    
    if (date.equals("") && thursday) {
      dates[0] = "TH";
    }
    else {
      
      String[] days = date.split("(?!^)"); //method of splitting by character taken from stackoverflow 
      //(http://stackoverflow.com/questions/5235401/split-string-into-array-of-character-strings)
      for (int i = 0; i < days.length; i++) {
        dates[i] = days[i]; //copies all of the characters split from date and transfers it to dates
      }
      
      if (thursday) {
        dates[days.length] = "TH"; //adds TH at the end of the array
      }
    }
    
    return dates;
  }
  
  public String getTime(){
    return time;
  }
  
  public String getProf(){
    return professor;
  }
  
  public String getSection(){
    return section;
  }
  
  public String getTitle(){
    return title;
  }
  
  public String getName(){
    return name;
  }
  
  public String getEndTime() {
    return endTime;
  }
  
  public String getStartTime() {
    return startTime;
  }
  
  
  public String toString() {
    String r = "";
    r += "Title: " + title + "\nSession: " + section + "\nCRN: " + crn + "\nProfessor: " + professor + "\nTime: "
      + time;
    
    return r;
    
    
  }
  
  public static void main(String[] args) {
    String[] d = {"CS 111", "Intro to CS", "01", "Eni", "TH - 1:30PM-2:30PM"};
    Course c = new Course("56789", d);
//    System.out.println(c.getStartTime());
//    System.out.println(c.getEndTime());
    System.out.println(Arrays.toString(c.getDate()));
    System.out.println(c);
    
    
  }
}

