/* FinalClasses.java
 * Jennifer Wang, Isabelle Li, Shan Lu
 * CS 230 Final Project
 * This is the object that we will use to store the linked list of final course selections the user has made.
 */

import java.util.*;

public class FinalClasses {
  //instance variable
  private LinkedList<Course> finalResult;
  
  public FinalClasses(){
    finalResult = new LinkedList<Course>();
  }
  
  public void addClass(Course c) {
    finalResult.add(c);
  }
  
  public void removeClass(int i) {
    finalResult.remove(i);
  }
  
  public Course getClass(int i) {
    return finalResult.get(i);
  }
  
  public LinkedList<Course> getFinalClasses(){
    return finalResult;
  }

  public int getSize(){
    return finalResult.size();
  }
  
  public String toString() {
    String s = "";
    for (int i = 0; i < finalResult.size(); i++) {
      s += finalResult.get(i).toString() + "\n";
    }
    return s;
  }

}