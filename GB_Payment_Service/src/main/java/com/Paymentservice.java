package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Payment;

@Path("/payments")
public class Paymentservice {

	Payment pay = new Payment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String readItems() {		
		return pay.getPaymentDetails();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String retrieveFromIProjects(String purchaseData) {
		// Convert the input string to a JSON object
		JsonObject purchasejson = new JsonParser().parse(purchaseData).getAsJsonObject();
				
		String cardNumber = purchasejson.get("cardNumber").getAsString();
		int buyerId = purchasejson.get("buyerId").getAsInt();
		int poductId = purchasejson.get("poductId").getAsInt();	
		double amount = purchasejson.get("amount").getAsDouble();
		
		String output = pay.addpaymentdetails(cardNumber,buyerId,poductId,amount);
		return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String payData) {

		JsonObject jsonObject = new JsonParser().parse(payData).getAsJsonObject();
		int paymentId = jsonObject.get("pid").getAsInt();
		String output = pay.deletePayment(paymentId);
		return output;
	}
	
	
}
