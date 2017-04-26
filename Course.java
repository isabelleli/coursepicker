/* Course.java
 * Jennifer Wang, Isabelle Li, Shan Lu
 * CS 230 Final Project
 * Object to store information about an individual course
 */

import java.util.*;
public class Course{
  //instance variables
  private String crn;
  private String date;
  private String startTime;
  private String endTime;
  private String professor;
  private String session;
  private String title;
    
  public Course(String c, String[] info) { //second parameter is CourseInformation info;
    crn = c;
    String[] infoArray = info;
    //String[] infoArray = info.searchDetails(c); //questionable? may need to copy entire array instead of just reading off
    date = infoArray[0];
    endTime = infoArray[1]; 
    professor = infoArray[2];
    session = infoArray[3];
    startTime = infoArray[4];
    title = infoArray[5];

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
    String[] days = date.split("(?!^)"); //method of splitting by character taken from stackoverflow 
    //(http://stackoverflow.com/questions/5235401/split-string-into-array-of-character-strings)
    
    String[] dates = new String[5];
    
    for (int i = 0; i < days.length; i++) {
      dates[i] = days[i]; //copies all of the characters split from date and transfers it to dates
    }
    
    if (thursday) {
      dates[days.length] = "TH"; //adds TH at the end of the array
    }
    
    return dates;
  }
  
  public String getStartTime(){
    return startTime;
  }
  
  public String getEndTime(){
    return endTime;
  }
  
  public String getProf(){
    return professor;
  }
  
  public String getSession(){
    return session;
  }
  
  public String getTitle(){
    return title;
  }
  
  public String toString() {
    String r = "";
    r += "Title: " + title + "\nSession: " + session + "\nCRN: " + crn + "\nProfessor: " + professor + "\nTime: "
      + startTime + "-" + endTime + "\nDate: " + date;
    
    return r;
    
    
  }
  
  public static void main(String[] args) {
    String[] d = {"MTH", "09:50AM", "Eni", "01", "08:30AM","CS", "111"};
    String[] b = {"MTTHF", "10:50AM", "Eni", "01", "09:40AM","CS", "240"};
    Course c = new Course("56789", d);
    System.out.println(Arrays.toString(c.getDate()));
    Course f = new Course("56789", b);
    System.out.println(Arrays.toString(f.getDate()));
    System.out.println(c);
    System.out.println(f);

  }
}

