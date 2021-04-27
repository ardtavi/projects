
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.Scanner;

public class JSON_Reader {
    JSONParser parser = new JSONParser();
    public JSONObject ReadJson(JSONObject obj)
    {

            String id = (String) obj.get("patient_id");


            String activity = (String) obj.get("activity");


            String start = (String) obj.get("start");
            String newstart="";


                Scanner s = new Scanner(start);
                while(s.next()!=" ")
                {
                    s.useDelimiter("-");
                    newstart = newstart.concat(s.next());
                    s.next();
                }
                s.next();
                while(s.next()!="\t")
                {
                    s.useDelimiter(":");
                    newstart = newstart.concat(s.next());
                    s.next();
                }

            String end = (String) obj.get("end");


         String status="OK";
        JSONObject jo = new JSONObject();
        jo.put("patient_id: ", id);
        jo.put("activity ", activity);
        jo.put("start ", newstart);
        jo.put("end ", end);


           // System.out.println(end);





        return jo;
    }
}
