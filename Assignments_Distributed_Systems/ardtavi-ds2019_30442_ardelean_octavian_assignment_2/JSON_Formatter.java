import org.json.simple.JSONObject;

import java.util.Scanner;

public class JSON_Formatter {

    public JSONObject getLine (Scanner s) {
        String starttime = s.next();

        String newstart="";
        String newend="";



        Scanner s2 = new Scanner(starttime).useDelimiter("[-: ]+");
        while(s2.hasNext())
        {
            newstart = newstart.concat(s2.next());
        }


       /* newstart = newstart+s2.next());

        s2.useDelimiter(":");
        newstart = newstart.concat(s2.next());

        newstart = newstart.concat(s2.next());

*/
        /*
        while(s2.next()!=" ")
        {

            newstart = newstart.concat(s2.next());
            s2.next();
        }
        */
        //s2.next();
     //   s2.useDelimiter(":");
        /*while(s2.next()!="\t")
        {

            newstart = newstart.concat(s2.next());
            s2.next();
        }
         */
        s.next();
        String endtime = s.next();
        Scanner s3 = new Scanner(endtime).useDelimiter("[-: ]+");
        while(s3.hasNext())
        {
            newend = newend.concat(s3.next());
        }

        s.next();
        String activity = s.next();
        JSONObject jo = new JSONObject();
        jo.put("patient_id: ", "1");
        jo.put("activity ", activity);
        jo.put("start ", newstart);
        jo.put("end ", newend);

        return jo;
    }
}
