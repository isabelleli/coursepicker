/* SearchTest.java
 * CS 230 final project
 * Modified by: ili
 * Modified date: 04/24/17
 */

import java.util.*;

public class SearchTest {
  private LinkedList<Course> searchResults;
  public SearchTest() {
    String[] c = {"CS 111", "Intro to CS", "01", "Eni", "MWT - 9:50AM - 11:00AM"};
    //String[] d = {"MWTHF", "11:00AM", "Susan", "01", "09:50AM","CHIN", "101"};
    Course a = new Course("56789", c);
    //Course b = new Course("12345", d);
    searchResults = new LinkedList<Course>();
    searchResults.add(a);
    //searchResults.add(b);
  }
  
  public Course getClass(int index) {
    return searchResults.get(index);
  }
  
  public int getSize() {
    return searchResults.size();
  }
  
  public String toString() {
    String r = "";
    for (int i = 0; i < getSize(); i++) {
      r += searchResults.get(i);
    }
    return r;
  }
  
  public static void main(String[] args) {
    SearchTest bob = new SearchTest();
    System.out.println(bob);
    System.out.println(bob.getSize());
  }
}
