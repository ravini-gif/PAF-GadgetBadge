package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.product;

@Path("/Products")
public class productsService 
{
	product productObj = new product();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProducts() 
	{
		return productObj.readProducts();
		
	}
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProject(@FormParam("productName") String productName,
								 @FormParam("productPrice")String productPrice,
								 @FormParam("productDescription") String productDescription) {
		
		String output = productObj.insertProducts(productName, productPrice, productDescription);
		
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProduct(String productData) {
		
		//convert string to a JSON
		JsonObject obj = new JsonParser().parse(productData).getAsJsonObject();
		
		//read values
		String productID = obj.get("productID").getAsString();
		String productName = obj.get("productName").getAsString();
		String productPrice = obj.get("productPrice").getAsString();
		String productDescription = obj.get("productDescription").getAsString();

		String output = productObj.updateProducts(productID, productName, productPrice, productDescription);
		
		return output;
		
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProducts(String productsData) {
		
		//convert string to XML doc
		Document doc = Jsoup.parse(productsData,"",Parser.xmlParser());
		
		//read the value from element
		String productID=doc.select("productID").text();
		
		String output = productObj.deleteProduct(productID);
		
		return output;
		
		
	}
}
