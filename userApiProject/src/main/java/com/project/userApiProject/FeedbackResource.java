package com.project.userApiProject;

//for REST Service
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;

import java.util.List;

//for json
import com.google.gson.Gson;

@Path("/feedbacks")
public class FeedbackResource {

	FeedbackRepository fr = new FeedbackRepository();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Feedback> getAllUsers() {
	
		return fr.getAllFeedbacks();
	}
	
	@POST
	@Path("/feedback")
	@Consumes(MediaType.APPLICATION_JSON)
	public Feedback addUser(Feedback f1) {
		
		return fr.createFeedback(f1);
	}
	
	FeedbackRepository feedObj = new FeedbackRepository(); 
	@GET
	@Path("/readFeedback")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		return feedObj.readFeedbacks();
	 } 
}
