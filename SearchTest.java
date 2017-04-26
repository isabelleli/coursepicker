/* SearchTest.java
 * CS 230 final project
 * Modified by: ili
 * Modified date: 04/24/17
 */

import java.util.*;

public class SearchTest {
  private LinkedList<Course> searchResults;
  public SearchTest() {
    String[] c = {"MTH", "09:40AM", "Eni", "01", "08:30AM","CS", "111"};
    String[] d = {"MWTHF", "11:00AM", "Susan", "01", "09:50AM","CHIN", "101"};
    Course a = new Course("56789", c);
    Course b = new Course("12345", d);
    searchResults = new LinkedList<Course>();
    searchResults.add(a);
    searchResults.add(b);
  }
  
  public Course getClass(int index) {
    return searchResults.get(index);
  }
  
  public int getSize() {
    return searchResults.size();
  }
  
  public String toString() {
    return searchResults;
  }
  
  public static void main(String[] args) {
    SearchTest bob = new SearchTest();
    System.out.println(bob);
    System.out.println(bob.getSize());
  }
}
