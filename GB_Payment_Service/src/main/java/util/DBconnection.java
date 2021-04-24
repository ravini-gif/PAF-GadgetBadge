package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {

	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paymentservicedb", "root", "");

		} catch (Exception e) {

			e.printStackTrace();
		}

		return con;
	}
}
