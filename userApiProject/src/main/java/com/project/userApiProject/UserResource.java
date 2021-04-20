package com.project.userApiProject;

import jakarta.ws.rs.Path;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/users")
public class UserResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUser() {
		
		List<User> user = new ArrayList<User>();
		
		User u1 = new User();
		u1.setFname("Nuwan");
		u1.setLname("Perera");
		u1.setAddress("02,Kandy Road, Malabe");
		u1.setEmail("nuwan@gmail.com");
		u1.setContact(712345678);
		u1.setUsername("nuwan");
		u1.setPassword("1111");
		
		user.add(u1);
		
		return user;
	}
	
}
