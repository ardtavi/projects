import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.util.Scanner;

public class Recv {

    private final static String QUEUE_NAME = "tasks";

    public static int stringCompare(String str1, String str2)
    {

        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)str1.charAt(i);
            int str2_ch = (int)str2.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }

        // Edge case for strings like
        // String 1="Geeks" and String 2="Geeksforgeeks"
        if (l1 != l2) {
            return l1 - l2;
        }

        // If none of the above conditions is true,
        // it implies both the strings are equal
        else {
            return 0;
        }
    }

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            Scanner s = new Scanner(message).useDelimiter("\\s*activity \":\"\\s*");
            s.next();
            String activity=s.next();

            Scanner s2=new Scanner(activity).useDelimiter("\\s*\",\"\\s*");   //get only the activity
            String Sactivity=s2.next();

            Scanner s3=new Scanner(activity).useDelimiter("\\s*\"start \":\"\\s*");   //get only the activity
            s3.next();
            String activity3=s3.next();

            Scanner s4=new Scanner(activity3).useDelimiter("\\s*\",\"\\s*");   //get only the activity
            String Istart=s4.next();

            Scanner s5=new Scanner(activity).useDelimiter("\\s*\"end \":\"\\s*");   //get only the activity
            s5.next();
            String activity5=s5.next();

            Scanner s6=new Scanner(activity5).useDelimiter("\\s*\",\"\\s*");   //get only the activity
            String Iend=s6.next();

            Scanner s7=new Scanner(Iend).useDelimiter("\\s*\"}\\s*");   //get only the activity
            String Iend2=s7.next();

            System.out.println(" [x] Received '" + message + "'");


            long start = Long.valueOf(Istart);
            long end =Long.valueOf(Iend2);

            if(stringCompare(Sactivity,"Sleeping")==0  )
            {
                if((end%100000000)/1000000==(start%100000000)/1000000) {

                    if ( ((((end%1000000)/10000)*3600) + (((end%10000)/100)*60) + (end%100) )- ((((start%1000000)/10000)*3600) + (((start%10000)/100)*60) + (start%100) ) > 12*60*60) {

                        System.out.println("Rule 1 broken: Pacient is sleeping for more than 12 hours ");
                    }

                }
                else {
                    if ( ((((end%1000000)/10000)*3600) + (((end%10000)/100)*60) + (end%100) )- ((((start%1000000)/10000)*3600) + (((start%10000)/100)*60) + (start%100) ) > -(12*60*60)) {

                        System.out.println("Rule 1 broken: Pacient is sleeping for more than 12 hours ");
                    }
                }

            }
            else if(stringCompare(Sactivity,"Leaving")==0 )
            {
                if((end%100000000)/1000000==(start%100000000)/1000000) {

                    if ( ((((end%1000000)/10000)*3600) + (((end%10000)/100)*60) + (end%100) )- ((((start%1000000)/10000)*3600) + (((start%10000)/100)*60) + (start%100) ) > 12*60*60) {

                        System.out.println("Rule 2 broken: Pacient is leaving for more than 12 hours ");

                    }

                }
                else {
                    if ( ((((end%1000000)/10000)*3600) + (((end%10000)/100)*60) + (end%100) )- ((((start%1000000)/10000)*3600) + (((start%10000)/100)*60) + (start%100) ) > -( 12*60*60)) {
                        //System.out.println((end%100000000)/1000000);
                        //System.out.println(((((end%1000000)/10000)*3600) + (((end%10000)/100)*60) + (end%100) )- ((((start%1000000)/10000)*3600) + (((start%10000)/100)*60) + (start%100) ));
                        System.out.println("Rule 2 broken: Pacient is leaving for more than 12 hours ");
                    }
                }

            }
            else if(stringCompare(Sactivity,"Toileting")==0 )
            {
                if((end%100000000)/1000000==(start%100000000)/1000000) {

                    if ( ((((end%1000000)/10000)*3600) + (((end%10000)/100)*60) + (end%100) )- ((((start%1000000)/10000)*3600) + (((start%10000)/100)*60) + (start%100) ) > 60*60) {

                        System.out.println("Rule 3 broken: Pacient is toielting for more than a hour ");
                        //System.out.println(((((end%1000000)/10000)* ((end%10000)/100) *((end%100))) - (start%1000000)/10000) * ((start%10000)/100) *((start%100)));
                    }

                }
                else {
                    if ( ((((end%1000000)/10000)*3600) + (((end%10000)/100)*60) + (end%100) )- ((((start%1000000)/10000)*3600) + (((start%10000)/100)*60) + (start%100) ) > -(60*60)) {


                        System.out.println("Rule 3 broken: Pacient is toielting for more than a hour ");
                    }
                }

            }

           // System.out.println("CUSTOM MESSEGE " + Sactivity+ " " +start+ " "+end);
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
