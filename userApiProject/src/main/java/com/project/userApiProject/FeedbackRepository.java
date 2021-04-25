package com.project.userApiProject;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class FeedbackRepository {

	//connect to the database
	public Connection getconnection() {
		Connection con = null;
		
		//Provide the correct details: DBServer/DBName, username, password
		String url = "jdbc:mysql://localhost:3306/userapiproject";
		String username = "root";
		String password = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,username,password);
		}
		catch (Exception e){
			System.out.println(e);
		}
		System.out.println("success");
		return con;
	}
	
	List<Feedback> feedback;
	public FeedbackRepository() {
		
		
		
		feedback = new ArrayList<>();
		
		Feedback f1 = new Feedback();
		f1.setName("Nuwan");
		f1.setEmail("nuwan@gmail.com");
		f1.setContact(712345678);
		f1.setMessage("Good");
		
		feedback.add(f1);

	}
	
	public List<Feedback> getAllFeedbacks(){
		
		return feedback;
	}
	
	
	public Feedback createFeedback(Feedback f1) {
		
		// create a prepared statement
		String insertSql = "INSERT INTO `feedback`(`fid`, `name`, `email`, `contact`, `message`) VALUES (?,?,?,?,?)";
		Connection con = getconnection();
		try {
			PreparedStatement st = con.prepareStatement(insertSql);
			
			// binding values
			st.setInt(1, 0);
			st.setString(2, f1.name);
			st.setString(3, f1.email);
			st.setInt(4, f1.contact);
			st.setString(5, f1.message);
			
			String output = "Inserted successfully"; 
			
			// execute the statement
			st.executeUpdate();
		}
		catch (Exception e){
			System.out.println(e);
			String output = "Error";
		}
		
		
		feedback.add(f1);
		System.out.println(feedback);
		return f1;
	}

	public String readFeedbacks() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = getconnection(); 
	 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Feedback ID</th>" +
	 "<th>Name </th>" + 
	 "<th>Email</th>" +
	 "<th>Contact No</th>" +
	 "<th>Message</th></tr>"; 
	 
	 String query = "SELECT * FROM `feedback`"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String fid = Integer.toString(rs.getInt("fid")); 
	 String name = rs.getString("name"); 
	 String email = rs.getString("email"); 
	 String contact = Integer.toString(rs.getInt("contact")); 
	 String message = rs.getString("message"); 
	
	 // Add into the html table
	 output += "<tr><td>" + fid + "</td>"; 
	 output += "<td>" + name + "</td>"; 
	 output += "<td>" + email + "</td>"; 
	 output += "<td>" + contact + "</td>"; 
	 output += "<td>" + message + "</td>"; 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"+ "<input name='itemID' type='hidden' value='" + fid + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }

	
	
}
