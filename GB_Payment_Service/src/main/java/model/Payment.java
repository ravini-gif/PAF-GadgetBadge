package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import util.DBconnection;

public class Payment {

	private int paymentid;
	private String cardNumber;
	private int buyerId;
	private int productId;
	private double amount;
	private String date;
	private String time;

	DBconnection dbConnection = new DBconnection();

	public String addpaymentdetails(String cardNumber, int buyerId, int productId, double amount) {
		String output = "";
		int referencNumber;

		try {
			Connection conn = dbConnection.connect();
			if (conn == null) {
				output = " Error while Connecting to the database";
			}

			String query = " insert into payments(`pid`,`cardNo`, `buyerId`,`productId`,`amount`, `date`, `time`)"
					+ " values(?,?,?,?,?,?,?)";

			PreparedStatement preparedstatement = conn.prepareStatement(query);

			preparedstatement.setInt(1, 0);
			preparedstatement.setString(2, cardNumber);
			preparedstatement.setInt(3, buyerId);
			preparedstatement.setInt(4, productId);
			preparedstatement.setDouble(5, amount);

			Calendar calendar = Calendar.getInstance();
			java.util.Date currentDate = calendar.getTime();
			java.sql.Date date = new java.sql.Date(currentDate.getTime());
			preparedstatement.setDate(6, date);

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String ctime = sdf.format(cal.getTime()).toString();
			System.out.println(ctime);
			Time currentTime = Time.valueOf(ctime);
			preparedstatement.setTime(7, currentTime);

			preparedstatement.execute();
			conn.close();

			output = "Payment details Inserted SuccessFully";

		} catch (Exception e) {

			output = "Error while Inserting Payment details";
			e.printStackTrace();
		}

		return output;
	}

	
	public String getPaymentDetails() {
		String output = "";

		try {

			Connection conn = dbConnection.connect();
			if (conn == null) {
				output = " Error while Connecting to the database";
			}

			String query = " select * from payments";
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery(query);

			while (resultSet.next()) {

				String paymentId = Integer.toString(resultSet.getInt("pid"));
				String cardnumber = resultSet.getString("cardNo");
				int buyerid = resultSet.getInt("buyerId");
				int productid = resultSet.getInt("productId");
				double amount = resultSet.getDouble("amount");
				String date = resultSet.getString("date");
				String time = resultSet.getString("time");

				// creating jSON string
				output += "{ ";
				output += "paymentId : \" " + paymentId + "\", ";			
				output += "cardnumber : \" " + cardnumber + "\", ";
				output += "buyerid : \" " + buyerid + "\", ";
				output += "productid : \" " + productid + "\", ";
				output += "amount : \" " + amount + "\", ";
				output += "date : \" " + date + "\", ";
				output += "time : \" " + time + "\"} \n ";

			} // end of while

			// closing the connection
			conn.close();

		} catch (Exception e) {
			output = "Error while reading the payment details";
			e.printStackTrace();
		}
		return output;
	}
	
	public String deletePayment(int paymentId) {
		String output = "";
		try {
			Connection con = dbConnection.connect();
			if (con == null) {
				output = "Error while Connecfting to the database ";

			}

			String query = "delete from payments where pid=" + paymentId + " ";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.executeUpdate();
			con.close();

			output = " payment details  Removed SuccessFully !!!";

		} catch (Exception e) {

			output = " error while deleting  payment details";
			e.printStackTrace();
		}

		return output;
	}

	
}
