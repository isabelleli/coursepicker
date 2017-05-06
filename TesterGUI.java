/* TesterGUI.java
 * Used to test ScheduleTabPanel
 * Modified by: ili
 * Modifidied date: 04/22/17
 */
import javax.swing.JFrame;

public class TesterGUI {
  
  public static void main(String[] args) {
    
    JFrame frame = new JFrame("Testing");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //create a panel, and add it to the frame
    SearchTest test = new SearchTest();
    ScheduleTabPanel2 panel = new ScheduleTabPanel2(test, fin);
    //Tester panel = new Tester(test);
    frame.getContentPane().add(panel);
    
    frame.pack();
    frame.setVisible(true);
  }
}