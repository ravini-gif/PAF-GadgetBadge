package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fund {

	private Connection connect(){
		
	      Connection con = null;
	    
	      try{
	              Class.forName("com.mysql.jdbc.Driver");

			 //Provide the correct details: DBServer/DBName, username, password
			      con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fund", "root", "Kaush,43");
			      
	      }catch (Exception e){
	    	  
	    	  e.printStackTrace(); }
	     
	      return con;
	 }
	
	public String insertFund(String Pname, String inventorName,String BaccNo , String amount){
	 
		String output = "";
	   
		try {
	
		       Connection con = connect();
	 
	           if (con == null)
	          {return "Error while connecting to the database for inserting."; }
	 
	           // create a prepared statement
	          String query = " insert into fund(`FID`,`Pname`,`inventorName`,`BaccNo`,`amount`)"
	          + " values (?, ?, ?, ?, ?)";
	          PreparedStatement preparedStmt = con.prepareStatement(query);
	
			  // binding values
			  preparedStmt.setInt(1, 0);
			  preparedStmt.setString(2, Pname);
			  preparedStmt.setString(3, inventorName);
			  preparedStmt.setString(4, BaccNo);
			  preparedStmt.setDouble(5, Double.parseDouble(amount));
	
	
	         // execute the statement
	
	         preparedStmt.execute();
	         con.close();
	         output = "Inserted successfully";
	   }catch (Exception e){
	    
		   output = "Error while inserting the fund.";
	       System.err.println(e.getMessage());
	   }
	       return output;
	 }
	
	 public String readFund() {
	 
		 String output = "";
	     
		 try{
			 
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Project Name</th>" +
			 "<th>Inventor name</th>" +
			 "<th>Bank Account No</th>" +
			 "<th>Fund Amount</th>" +
			 "<th>Update</th><th>Remove</th></tr>";
	
			 String query = "select * from fund";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
		
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String fundID = Integer.toString(rs.getInt("FID"));
				 String ProName = rs.getString("Pname");
				 String inventorName = rs.getString("inventorName");
				 String bankAccNo = rs.getString("BaccNo");
				 String fundAmount = Double.toString(rs.getDouble("amount"));
				
				 
				 // Add into the html table
				 output += "<tr><td>" + ProName  + "</td>";
				 output += "<td>" + inventorName + "</td>";
				 output += "<td>" + bankAccNo + "</td>";
				 output += "<td>" + fundAmount + "</td>";
				 
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
				 + "<input name='itemID' type='hidden' value='" + fundID
				 + "'>" + "</form></td></tr>";
	         }
	         con.close();
	        
	         // Complete the html table
	         output += "</table>";
	   }catch (Exception e){
	 
		 output = "Error while reading the items.";
	     System.err.println(e.getMessage());
	  }
	      return output;
   }
	
	
	public String updateFund(String fid,String Pname, String inventorName,String BaccNo, String amount) {
	     
		String output = "";
		 try
		 {
			 Connection con = connect();
			
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 
			 // create a prepared statement
			 String query = "UPDATE fund SET Pname=?,inventorName=?,BaccNo=?,amount=? WHERE FID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setString(1, Pname);
			 preparedStmt.setString(2, inventorName); 
			 preparedStmt.setString(4, BaccNo);
			 preparedStmt.setDouble(3, Double.parseDouble(amount));
			 preparedStmt.setInt(5, Integer.parseInt(fid));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
		 }catch (Exception e)
		 {
			 output = "Error while updating the item.";
			 System.err.println(e.getMessage());
		 }
	 
		 return output;
	 }
	
	public String deleteFund(String FID){
		
	 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
			 // create a prepared statement
			 String query = "delete from fund where FID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(FID));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Deleted successfully";
		 }
		 catch (Exception e){
		 
			 output = "Error while deleting the item.";
		     System.err.println(e.getMessage());
		 }
	    
		 return output;
	 } 
	
}
