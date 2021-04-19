package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Project {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projectservice","root","1234");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertProject(String projectname,String projectdescription,Double requiredfund,String projectduration) {
		
		String output = "";
		try {
			Connection con = connect();
			
			if(con==null) {
				return "Error while connecting";
			}
			
			String query = "insert into project (id,projectname,projectdescription,requiredfund,projectduration)" 
			+ "values(?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, projectname);
			preparedStatement.setString(3, projectdescription);
			preparedStatement.setDouble(4, requiredfund);
			preparedStatement.setString(5, projectduration);
			
			preparedStatement.execute();
			con.close();
			
			output = "Inserted Successfully";

		}catch(Exception e){
			output ="Error while inserting to DB";
			System.err.println(e.getMessage());
			System.out.println("+++++++++++++++++++"+e.getMessage());
		}
		
		return output;
	}
	
	public String readProjects() {
		
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting";
			}
			
			output = "<table border='1'><tr><th>Name</th><th>Description</th>" +
					 "<th>Required Fund</th>" + 
					 "<th> Duration</th>" +
					 "<th>Update</th><th>Remove</th></tr>";  
			
			String query = "Select * from project";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			while(rs.next()) {
				String id=Integer.toString(rs.getInt("id"));
				String projectname= rs.getString("projectname");
				String projectdescription=rs.getString("projectdescription");
				String requiredfund = Double.toString(rs.getDouble("requiredfund"));
				String projectduration = rs.getString("projectduration");
				
				output += "<tr><td>" + projectname + "</td>"; 
				output += "<td>" + projectdescription + "</td>"; 
				output += "<td>" + requiredfund + "</td>"; 
				output += "<td>" + projectduration + "</td>"; 
				
			}
			
			con.close();
			
			output += "</table>";
			
		}catch(Exception e) {
			output = "Error while reading";
			System.err.println(e.getMessage());
			System.out.println("++++++++++++++++++++"+e.getMessage());
		}
		
		return output;
	}
	
	public String updateProjects(String id,String projectname,String projectdescription,Double requiredfund,String projectduration) {
		
		String output ="";
		
		try {
			Connection con = connect();
			if(con ==null) {
				return"Error while connecting to DB";
			}
			
			String query = "UPDATE project SET projectname=?, projectdescription=?,requiredfund=?,projectduration=? WHERE id=?";
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
			preparedStatement.setString(1, projectname);
			preparedStatement.setString(2, projectdescription);
			preparedStatement.setDouble(3,requiredfund);
			preparedStatement.setString(4, projectduration);
			preparedStatement.setInt(5, Integer.parseInt(id));
			
			preparedStatement.execute();
			con.close();
			
			output="Updated successfully";
			
		}catch(Exception e){
			output ="Error while updating projects";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteProjects (String id) {
		
		String output ="";
		
		try {
			
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to DB";
			}
			
			String query = "DELETE FROM project where id=?";
			
			PreparedStatement preparedStatement =con.prepareStatement(query);
			
			preparedStatement.setInt(1, Integer.parseInt(id));
			
			preparedStatement.execute();
			con.close();
			
			output="Project Deleted successfully";
			
		}catch(Exception e){
			output="Error while deleting the project";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

}
