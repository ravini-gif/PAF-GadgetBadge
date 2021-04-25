package com.project.userApiProject;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UserRepository {
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
	
	List<User> user;
	public UserRepository() {
		
		
		
		user = new ArrayList<>();
		
		User u1 = new User();
		u1.setFname("Nuwan");
		u1.setLname("Perera");
		u1.setAddress("02,Kandy Road, Malabe");
		u1.setEmail("nuwan@gmail.com");
		u1.setContact(712345678);
		u1.setUsername("nuwan");
		u1.setPassword("1111");
		
		user.add(u1);

	}
	
	public List<User> getAllUsers(){
		
		return user;
	}
	
	public User createUser(User u1) {
		
		// create a prepared statement

		String insertSql = "INSERT INTO `users`(`id`, `fname`, `lname`, `address`, `email`, `contact`, `username`, `password`) VALUES (?,?,?,?,?,?,?,?)";
		Connection con = getconnection();
		try {
			PreparedStatement st = con.prepareStatement(insertSql);
			
			// binding values
			st.setInt(1, 0);
			st.setString(2, u1.fname);
			st.setString(3, u1.lname);
			st.setString(4, u1.address);
			st.setString(5, u1.email);
			st.setInt(6, u1.contact);
			st.setString(7, u1.username);
			st.setString(8, u1.password);
			
			String output = "Inserted successfully"; 
			
			// execute the statement
			st.executeUpdate();
		}
		catch (Exception e){
			System.out.println(e);
			String output = "Error";
		}
		
		
		user.add(u1);
		System.out.println(user);
		return u1;
	}

	public User getUserId(int id) {

		//read data from the database
		String getsql = "SELECT * FROM `users` WHERE id = '"+id+"' ";
		User ud = new User();
		Connection con = getconnection();
		
		try {
			Statement st = con.createStatement();
			ResultSet u1 = st.executeQuery(getsql);
			
			while(u1.next()) {
				
				ud.setId(u1.getInt(1));
				ud.setFname(u1.getString(2));
				ud.setLname(u1.getString(3));
				ud.setAddress(u1.getString(4));
				ud.setEmail(u1.getString(5));
				ud.setContact(u1.getInt(6));
				ud.setUsername(u1.getString(7));
				ud.setPassword(u1.getString(8));
			}
			
		
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return ud;
		
	}

	public String deleteUser(int id) {

		String output ="";
		try {
			Connection con = getconnection();
			
			// create a prepared statement
			String deleteUser = "DELETE FROM `users` WHERE id = '"+id+"' ";
			PreparedStatement ps = con.prepareStatement(deleteUser);
			ps.execute();
			
			output = "Delete Successful";
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return output;
	}

	public String updateUser(User user) {
		String output ="";
		try {
			Connection con = getconnection();
			
			// create a prepared statement

			String updateUser = "UPDATE `users` SET `id`='"+user.getId()+"',`fname`='"+user.getFname()+"',`lname`='"+user.getLname()+"',`address`='"+user.getAddress()+"',`email`='"+user.getEmail()+"',`contact`='"+user.getContact()+"',`username`='"+user.getUsername()+"',`password`='"+user.getPassword()+"' WHERE `id`='"+user.getId()+"' ";
			PreparedStatement st = con.prepareStatement(updateUser);

			// execute the statement
			st.executeUpdate();
			output = "Update Successfully";
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
			output = "Error";
		}
		return output;
	}
	
		
		
		
		
	public String readUsers() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = getconnection(); 
	 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>User ID</th>" +
	 "<th>first Name </th>" + 
	 "<th>Last Name</th>" +
	 "<th>Address</th>" +
	 "<th>Email</th>" +
	 "<th>Contact No</th>" +
	 "<th>Username</th>" +
	 "<th>Password</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "SELECT * FROM `users`"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String id = Integer.toString(rs.getInt("id")); 
	 String fname = rs.getString("fname"); 
	 String lname = rs.getString("lname"); 
	 String address = rs.getString("address"); 
	 String email = rs.getString("email"); 
	 String contact = Integer.toString(rs.getInt("contact")); 
	 String username = rs.getString("username"); 
	 String password = rs.getString("password"); 
	
	 // Add into the html table
	 output += "<tr><td>" + id + "</td>"; 
	 output += "<td>" + fname + "</td>"; 
	 output += "<td>" + lname + "</td>"; 
	 output += "<td>" + address + "</td>"; 
	 output += "<td>" + email + "</td>"; 
	 output += "<td>" + contact + "</td>"; 
	 output += "<td>" + username + "</td>"; 
	 output += "<td>" + password + "</td>"; 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"+ "<input name='itemID' type='hidden' value='" + id + "'>" + "</form></td></tr>"; 
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
