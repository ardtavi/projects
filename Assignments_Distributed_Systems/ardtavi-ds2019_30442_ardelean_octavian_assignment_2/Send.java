import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.File;
import java.util.Scanner;

import org.json.simple.JSONObject;
public class Send {

    private final static String QUEUE_NAME = "tasks";


    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        int count=0;
        File file = new File("F:\\Faculta\\AN4\\DS\\Assignment2\\activity.txt");
        Scanner scan = new Scanner(file);
        String fileContent="";
        while(scan.hasNextLine()) {
            //fileContent=fileContent.concat(scan.nextLine());

            Scanner s = new Scanner(scan.nextLine()).useDelimiter("\t");
            JSON_Formatter broker_write = new JSON_Formatter();
            JSONObject jo= broker_write.getLine(s);

//            String starttime=s.next();
//            s.next();
//            String endtime=s.next();
//            s.next();
//            String activity=s.next();
//            JSONObject jo = new JSONObject();
//            jo.put("patient_id: ", "1");
//            jo.put("activity: ", activity);
//            jo.put("start: ", starttime);
//            jo.put("end: ", endtime);

            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                JSONObject message = jo;
                count++;
                channel.basicPublish("", QUEUE_NAME, null, message.toString().getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + jo + "'");
                Thread.sleep(1);

            }
            //fileContent="";
        }
    }
}
