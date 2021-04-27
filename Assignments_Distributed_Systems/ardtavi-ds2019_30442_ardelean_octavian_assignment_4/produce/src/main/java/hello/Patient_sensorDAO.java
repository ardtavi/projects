package hello;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Patient_sensorDAO {
    private Connection connection;
    private Statement statement;

    public Patient_sensorDAO() {
    }

    public List<Patient_sensor> getPatient_sensors() throws SQLException {
        String query = "SELECT * FROM patient_sensor";
        List<Patient_sensor> list = new ArrayList<Patient_sensor>();
        Patient_sensor patient_sensor = null;
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                patient_sensor = new Patient_sensor();
				/*Retrieve one patient_sensor details 
				and store it in patient_sensor object*/
                patient_sensor.setPatient_id(rs.getInt("patient_id"));
                patient_sensor.setActivity(rs.getString("activity"));
                patient_sensor.setEnd(rs.getString("end"));
                patient_sensor.setStart(rs.getString("start"));


                //add each patient_sensor to the list
                list.add(patient_sensor);
            }
        } finally {
            DbUtil.close(rs);
            DbUtil.close(statement);
            DbUtil.close(connection);
        }
        return list;
    }
}