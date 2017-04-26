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
  
  public void removeClass(Course c) {
    for (int i = 0; i < finalResult.size(), i ++){
      if (finalResult.get(i).equals(c)) {
        finalResult.remove(i);
      }
    }
  
  }
  
  public LinkedList<String[]> getFinalClasses(){
    return finalResult;
  }



}