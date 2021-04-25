package com.project.userApiProject;

import java.util.ArrayList;
import java.util.List;

//for json
import com.google.gson.Gson;

//for REST service
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;


@Path("/users")
public class UserResource {

	
	UserRepository ur = new UserRepository();
	
	
	//@GET
	//@Produces(MediaType.APPLICATION_JSON)
	//public List<User> getAllUsers() {
	
	//	return ur.getAllUsers();
	//}
	
	@POST
	@Path("/user")
	@Consumes(MediaType.APPLICATION_JSON)
	public User addUser(User u1) {
		
		return ur.createUser(u1);
	}
	

	@GET
	@Path("/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser(@PathParam("id") String id) {
		User res = new User();
	res = ur.getUserId(Integer.parseInt(id));
	Gson test = new Gson();
	String jsonObject = test.toJson(res);
	return jsonObject;
	}
	
	@DELETE
	@Path("/deleteUser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteuser(@PathParam("id") int id) {
		return ur.deleteUser(id);
	}
	
	@PUT
	@Path("/updateUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(User u1) 
	{ 
	
	 return ur.updateUser(u1);
	}
	
	UserRepository userObj = new UserRepository(); 
	@GET
	@Path("/User")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		return userObj.readUsers();
	 } 
	
}
