package com.example.consumingwebservice;

import com.example.consumingwebservice.wsdl.Activity;
import com.example.consumingwebservice.wsdl.GetActivitiesResponse;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@SpringBootApplication
public class ConsumingWebServiceApplication extends Application {

    private static List<Activity> activities = new ArrayList<Activity>();

    public static void main(String[] args) {
        SpringApplication.run(ConsumingWebServiceApplication.class, args);
    }


    public int stringCompare(String str1, String str2) {

        int l1 = str1.length();
        int l2 = str2.length();
        int lmin = Math.min(l1, l2);

        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int) str1.charAt(i);
            int str2_ch = (int) str2.charAt(i);

            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }
        if (l1 != l2) {
            return l1 - l2;
        } else {
            return 0;
        }
    }

    @Override
    public void start(Stage stage) throws Exception {


        Scene scene = new Scene(new Group(), Color.BEIGE);
        stage.setTitle("Activity History");
        stage.setWidth(1000);
        stage.setHeight(500);


        ListView listView = new ListView();
        listView.setLayoutX(500);
        listView.setMaxWidth(1000);


        List<String> activityNames = activities.stream().map(Activity::getActivityLabel).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());

        List<PieChart.Data> data = activityNames.stream().map(activityName -> {
            AtomicInteger hoursSum = new AtomicInteger();
            activities.forEach(activity -> {
                if (activity.getActivityLabel().equals(activityName)) {
                    hoursSum.addAndGet((getHoursDifference(activity)));
                }

                long start = Long.valueOf(activity.getStartTime());
                long end = Long.valueOf(activity.getEndTime());
                long nr = 10000000000L;
                if (stringCompare(activity.getActivityLabel(), "Sleeping") == 0) {
                    if ((end % 100000000) / 1000000 == (start % 100000000) / 1000000) {

                        if (((((end % 1000000) / 10000) * 3600) + (((end % 10000) / 100) * 60) + (end % 100)) - ((((start % 1000000) / 10000) * 3600) + (((start % 10000) / 100) * 60) + (start % 100)) > 10 * 60 * 60) {

                            String str = "Rule 1 broken: Pacient is sleeping for more than 12 hours in sarting date: " + start / nr + "-" + start / 100000000 % 100 + "-" + start / 1000000 % 100;
                            listView.getItems().add(str);
                            System.out.println("Rule 1 broken: Pacient is sleeping for more than 12 hours in sarting date: " + start / nr + "-" + start / 100000000 % 100 + "-" + start / 1000000 % 100);
                        }

                    } else {
                        if (((((end % 1000000) / 10000) * 3600) + (((end % 10000) / 100) * 60) + (end % 100)) - ((((start % 1000000) / 10000) * 3600) + (((start % 10000) / 100) * 60) + (start % 100)) > -(12 * 60 * 60)) {

                            String str = "Rule 1 broken: Pacient is sleeping for more than 12 hours in sarting date: " + start / nr + "-" + start / 100000000 % 100 + "-" + start / 1000000 % 100;
                            listView.getItems().add(str);
                            System.out.println("Rule 1 broken: Pacient is sleeping for more than 12 hours in sarting date: " + start / nr + "-" + start / 100000000 % 100 + "-" + start / 1000000 % 100);
                        }
                    }

                } else if (stringCompare(activity.getActivityLabel(), "Leaving") == 0) {
                    if ((end % 100000000) / 1000000 == (start % 100000000) / 1000000) {

                        if (((((end % 1000000) / 10000) * 3600) + (((end % 10000) / 100) * 60) + (end % 100)) - ((((start % 1000000) / 10000) * 3600) + (((start % 10000) / 100) * 60) + (start % 100)) > 12 * 60 * 60) {

                            System.out.println("Rule 2 broken: Pacient is leaving for more than 12 hours ");

                        }

                    } else {
                        if (((((end % 1000000) / 10000) * 3600) + (((end % 10000) / 100) * 60) + (end % 100)) - ((((start % 1000000) / 10000) * 3600) + (((start % 10000) / 100) * 60) + (start % 100)) > -(12 * 60 * 60)) {
                            //System.out.println((end%100000000)/1000000);
                            //System.out.println(((((end%1000000)/10000)*3600) + (((end%10000)/100)*60) + (end%100) )- ((((start%1000000)/10000)*3600) + (((start%10000)/100)*60) + (start%100) ));
                            System.out.println("Rule 2 broken: Pacient is leaving for more than 12 hours ");
                        }
                    }

                } else if (stringCompare(activity.getActivityLabel(), "Toileting") == 0) {
                    if ((end % 100000000) / 1000000 == (start % 100000000) / 1000000) {

                        if (((((end % 1000000) / 10000) * 3600) + (((end % 10000) / 100) * 60) + (end % 100)) - ((((start % 1000000) / 10000) * 3600) + (((start % 10000) / 100) * 60) + (start % 100)) > 60 * 60) {

                            String str = "Rule 3 broken: Pacient is toielting for more than a hour ";
                            listView.getItems().add(str);
                            System.out.println("Rule 3 broken: Pacient is toielting for more than a hour ");
                            //System.out.println(((((end%1000000)/10000)* ((end%10000)/100) *((end%100))) - (start%1000000)/10000) * ((start%10000)/100) *((start%100)));
                        }

                    } else {
                        if (((((end % 1000000) / 10000) * 3600) + (((end % 10000) / 100) * 60) + (end % 100)) - ((((start % 1000000) / 10000) * 3600) + (((start % 10000) / 100) * 60) + (start % 100)) > -(60 * 60)) {


                            System.out.println("Rule 3 broken: Pacient is toielting for more than a hour ");
                        }
                    }

                }

            });
            return new PieChart.Data(activityName, hoursSum.get());
        }).collect(Collectors.toList());

        ObservableList<PieChart.Data> observableData = FXCollections.observableList(data);

        final PieChart chart = new PieChart(observableData);
        chart.setTitle("Activities");

        ((Group) scene.getRoot()).getChildren().add(chart);
        ((Group) scene.getRoot()).getChildren().add(listView);
        stage.setScene(scene);
        stage.show();
    }

    @Bean
    CommandLineRunner lookup(ActivitiesClient quoteClient) {
        return args -> {
            GetActivitiesResponse response = quoteClient.getActivities();
            System.out.println("first activity: " + response.getActivities().get(0).getActivityLabel());

            activities.addAll(response.getActivities());

            launch();


            System.err.println(response.getActivities());
        };
    }

    public int getHoursDifference(Activity activity) {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        Date start_time = null;
//        Date end_time = null;
        String start_time;
        String end_time;
        //            start_time = formatter.parse(activity.getStartTime().toString().replace("T", " "));
//            end_time = formatter.parse(activity.getEndTime().toString().replace("T", " "));
//            long diff = end_time.getTime() - start_time.getTime();
        start_time = activity.getStartTime();
        end_time = activity.getEndTime();

        long start = Long.valueOf(start_time);
        long end = Long.valueOf(end_time);
        int diff = Integer.parseInt(
                String.valueOf(((((end % 1000000) / 10000) * 3600) + (((end % 10000) / 100) * 60) + (end % 100)) - ((((start % 1000000) / 10000) * 3600) + (((start % 10000) / 100) * 60) + (start % 100))));
        //  int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
        //int diffhours = (int) (diff / (60 * 60 * 1000));
        //  int diffmin = (int) (diff / (60 * 1000));


        return diff;


    }

}
