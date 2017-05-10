/* FinalClasses.java
 * Jennifer Wang, Isabelle Li, Shan Lu
 * CS 230 Final Project
 * This is the object that we will use to store the linked list of final course selections the user has made.
 * Modified by: jwang17
 * Modified date: 05/07/17
 */

import java.util.*;

public class FinalClasses {
  //instance variable
  private LinkedList<Course> finalResult;
  
  public FinalClasses(){
    //constructor creates new empty finalResult linked list
    finalResult = new LinkedList<Course>();
  }
  
  public void addClass(Course c) {
    //adds class to finalResult
    finalResult.add(c);
  }
  
  public void removeClass(int i) {
    //removes class at index i from finalResult
    finalResult.remove(i);
  }
  
  public Course getClass(int i) {
    //gets the class at index i in finalResult
    return finalResult.get(i);
  }
  
  public LinkedList<Course> getFinalClasses(){
    //gets the linked list finalResult
    return finalResult;
  }

  public int getSize(){
    //returns the size of finalResult (number of classes selected)
    return finalResult.size();
  }
  
  public String toString() {
    //toString method formatted to print classes in finalResult
    String s = "";
    for (int i = 0; i < finalResult.size(); i++) {
      s += finalResult.get(i).toString() + "\n";
    }
    return s;
  }

}