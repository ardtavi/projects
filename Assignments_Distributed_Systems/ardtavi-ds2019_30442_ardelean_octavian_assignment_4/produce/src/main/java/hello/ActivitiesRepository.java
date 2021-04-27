package hello;

import io.spring.guides.gs_producing_web_service.Activity;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActivitiesRepository {
    public final List<Activity> activities = new ArrayList<>();

    String query = "SELECT * FROM patient_sensor";

    @PostConstruct
    public void initData() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                int patient_id = rs.getInt("patient_id");
                String startTime = rs.getString("start");
                String endTime = rs.getString("end");
                String activityLabel = rs.getString("activity");
                //int id = rs.getInt("id");

                Activity activity = new Activity();
                activity.setPatientId(patient_id);
                activity.setStartTime(startTime);
                activity.setEndTime(endTime);
                activity.setActivityLabel(activityLabel);
                //activity.setId(id);

                activities.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
