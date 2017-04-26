/* Time.java
 * CS 230 final project
 * Modified by: ili
 * Modified date: 04/25/17
 */

public class Time {
  private String hours;
  private String minutes;
  
  public Time(String time) { //String argument must be formated so that it reflects a 12-hour clock
    String[] parts = time.split(":");
    hours = parts[0];
    minutes = parts[1];
  }
  
  public String getHours() {
    return hours;
  }
  
  public String getMinutes() {
    return minutes;
  }
  
  public void setHours(String h) {
    hours = h;
  }
  
  public void setMinutes(String min) {
    minutes = min;
  }
  
  public void roundToNearestHalfHour() {
    int round = 0;
    int m = Integer.parseInt(minutes);
    if (m != 0 && m != 30) {
      if (m < 30) {
        round = m/15;
        
        if (round == 0) 
          setMinutes("00");
        else 
          setMinutes("30");
      }
      else if (m > 30) {
        round = m/45;
        
        if (round == 0) 
          setMinutes("30");
        else {
          int hr = Integer.parseInt(hours);
          hr = (hr + 1) % 12; //hours always reflect a 12-hour clock
          //hr is increased by one to represent rounding up to the next hour
          if (hr < 10) 
            hours = "0" + hr; 
          else
            hours = Integer.toString(hr);
          
          setMinutes("00");
        }
      }
    }
  }

  public String toString() {
    return (hours + ":" + minutes);
  }
  
  public static void main(String[] args) {
    Time t = new Time("8:46");
    Time s = new Time("12:12");
    Time d = new Time("04:16");
    System.out.println(t.getHours());
    System.out.println(t.getMinutes());
    t.roundToNearestHalfHour();
    s.roundToNearestHalfHour();
    d.roundToNearestHalfHour();
    System.out.println(t);
    System.out.println(s);
    System.out.println(d);
  }
}
