package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.BankCard;

@Path("/bankcard")
public class BankCardService {

	BankCard card = new BankCard();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String readAllbankCards() {
		
		return card.readBankcards();
	}
	
	

	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String readItems(String userIdjson) {

		JsonObject cardObject1 = new JsonParser().parse(userIdjson).getAsJsonObject();
	
		int userId = cardObject1.get("userId").getAsInt();
		System.out.println(userId);
		return card.readCardDetails(userId);
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCardDetails(String bankcard) {
		
		JsonObject jsonObj = new JsonParser().parse(bankcard).getAsJsonObject();

		// Read the values from the JSON object

		int userId = jsonObj.get("userId").getAsInt();
		String nameOnCard = jsonObj.get("nameOnCard").getAsString();
		String cardNumber = jsonObj.get("cardNumber").getAsString();
		String expDate = jsonObj.get("expiredate").getAsString();
		int secCode = jsonObj.get("securityCode").getAsInt();
		int postalCode = jsonObj.get("postalCode").getAsInt();

		String output = card.addBankCard(userId, nameOnCard, cardNumber, expDate, secCode, postalCode);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String cardData) {

		System.out.println("Inside delete");
		JsonObject cardObject = new JsonParser().parse(cardData).getAsJsonObject();
		int userId = cardObject.get("userId").getAsInt();
		String output = card.removeBankCard(userId);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCardDetails(String itemData) {

		JsonObject cardObject = new JsonParser().parse(itemData).getAsJsonObject();
		int userId = cardObject.get("userId").getAsInt();
		String nameOnCard = cardObject.get("nameOnCard").getAsString();
		String cardNumber = cardObject.get("cardNumber").getAsString();
		String expDate = cardObject.get("expiredate").getAsString();
		int secCode = cardObject.get("securityCode").getAsInt();
		int postalCode = cardObject.get("postalCode").getAsInt();
		String output = card.updatebankcard(userId, nameOnCard, cardNumber, expDate, secCode, postalCode);
		return output;
	}
	
}
