package com;
import model.Fund;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Funds") 
public class FundService {

	Fund fundObj = new Fund();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		return fundObj.readFund();
	 } 
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFund(@FormParam("Pname") String projectname,
								 @FormParam("inventorName")String inventorName,
								 @FormParam("BaccNo") String bankAccNo,
								 @FormParam("amount") String fundAmount) {
		
		String output = fundObj.insertFund(projectname, inventorName, bankAccNo, fundAmount);
		
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFund(String fundData) {
		
		//convert string to a JSON
		JsonObject obj = new JsonParser().parse(fundData).getAsJsonObject();
		
		//read values
		String Fid = obj.get("FID").getAsString();
		String projectname = obj.get("Pname").getAsString();
		String inventorName = obj.get("inventorName").getAsString();
		String BankAccNo = obj.get("BaccNo").getAsString();
		String fundAmount = obj.get("amount").getAsString();

		String output = fundObj.updateFund(Fid, projectname, inventorName, BankAccNo, fundAmount);
		
		return output;
		
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFund(String fundData) {
		
		//convert string to XML doc
		Document doc = Jsoup.parse(fundData,"",Parser.xmlParser());
		
		//read the value from element
		String id= doc.select("FID").text();
		
		String output = fundObj.deleteFund(id);
		
		return output;
		
		
	}
}
