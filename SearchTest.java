/* SearchTest.java
 * CS 230 final project
 * Modified by: ili
 * Modified date: 04/24/17
 */

import java.util.*;

public class SearchTest {
  private String[][] searchResults;
  public SearchTest() {
    String[] cs1 = {"CS 111 01", "08:30AM", "09:40AM", "MTH", "Eni"};
    String[] cs2 = {"CS 115 02", "08:30AM", "09:40AM", "TF", "Stella"};
    searchResults = new String[][]{cs1, cs2};
  }
  
  public String[] getClass(int index) {
    return searchResults[index];
  }
  
  public int getSize() {
    return searchResults.length;
  }
  public String toString() {
    String r = "";
    for (int i = 0; i < searchResults.length; i++) {
      r += Arrays.toString(searchResults[i]);
    }
    return r;
  }
  
  public static void main(String[] args) {
    SearchTest bob = new SearchTest();
    System.out.println(bob);
    System.out.println(bob.getSize());
  }
}
