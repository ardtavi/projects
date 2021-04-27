package hello;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActivityRepository {
    public final List<Patient_sensor> activities = new ArrayList<>();

    String query = "SELECT * FROM activity";

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

                Patient_sensor activity = new Patient_sensor();
                activity.setPatient_id(patient_id);
                activity.setStart(startTime);
                activity.setEnd(endTime);
                activity.setActivity(activityLabel);
                //activity.setId(id);

                activities.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//	@PostConstruct
//	public void initData() {
//		Activity spain = new Activity();
//		spain.setName("Spain");
//		spain.setCapital("Madrid");
//		spain.setCurrency(Currency.EUR);
//		spain.setPopulation(46704314);
//
//		countries.put(spain.getName(), spain);
//
//		Activity poland = new Activity();
//		poland.setName("Poland");
//		poland.setCapital("Warsaw");
//		poland.setCurrency(Currency.PLN);
//		poland.setPopulation(38186860);
//
//		countries.put(poland.getName(), poland);
//
//		Activity uk = new Activity();
//		uk.setName("United Kingdom");
//		uk.setCapital("London");
//		uk.setCurrency(Currency.GBP);
//		uk.setPopulation(63705000);
//
//		countries.put(uk.getName(), uk);
//	}

//	public Activity findActivity(String name) {
//		Assert.notNull(name, "The country's name must not be null");
//		return ac.get(name);
//	}
}
