

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuItem implements BaseProduct {

	@Override
	public int computeprice(int price) {


		String product="";
		String productPrice = "SELECT Price FROM product WHERE productName= '" + product + "' ; ";
        PreparedStatement statement = null;
        ResultSet rs = null;
     

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "1234");

            statement = conn.prepareStatement(productPrice);
            rs = statement.executeQuery();

            while (rs.next()) {
                price = Integer.parseInt(rs.getString(1));

            }

        } catch (SQLException exc) {

            exc.printStackTrace();
        }
        return price;
    }

		
	}
	