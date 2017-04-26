

//658(750-92)
//750

import java.util.*;
import org.json.*;
  
public class CourseInformation {
  
  //javac -cp /Users/Regina/Desktop/scraping/json-20160212.jar;
  
  public static void main (String[] args) {
    
    Hashtable<String, String> courseCRN = new Hashtable<String, String>(1000);// 1000 initial spots 
      
    String jsonFilePath = "/Users/Regina/Desktop/scraping/test.json";
//    Reader in = new InputStreamReader(new FileInputStream(jsonFilePath), StandardCharsets.UTF_8);
//    JSONArray json = new JSONArray(new JSONTokener(in));
//    in.close();
            
    JSONArray json = new JSONArray(jsonFilePath);
//    JSONParser parser = new JSONParser();
//    JSONObject json = new parser.parse(new FileReader("CourseInfo.json"));
    
//    Iterator<String> iterator = json.iterator();
//    while (iterator.hasNext()) {
//      JSONObject course = json.get(i);
//      String subjectNumber = course.getString("Subject") + " " + course.getString("Number");
//      String crn = course.getString("CRN");
//      courseCRN.put(subjectNumber, crn);
//    }
    
    for (int i=0;i<json.length();i++) {
      JSONObject course = json.getJSONObject(i);
      String subjectNumber = course.getString("Subject") + " " + course.getString("Number");
      String crn = course.getString("CRN");
      courseCRN.put(subjectNumber, crn);
    }
    
//    courseCRN.put("AFR 105","13587");
//    courseCRN.put("AFR 217","13813");
//    courseCRN.put("AFR 225","13812");
    System.out.println(courseCRN);
//    String str = courseCRN.get("AFR 105");
//    System.out.println(str);
  }
}

//import org.json.*;
//
//
//JSONObject obj = new JSONObject(" .... ");
//String pageName = obj.getJSONObject("pageInfo").getString("pageName");
//
//JSONArray arr = obj.getJSONArray("posts");
//for (int i = 0; i < arr.length(); i++)
//{
//    String post_id = arr.getJSONObject(i).getString("post_id");
//    ......
//}