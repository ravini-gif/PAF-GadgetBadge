package com;

import model.Project;

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

@Path("/Projects")
public class projectService {
	
	Project projectObj = new Project();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return projectObj.readProjects();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProject(@FormParam("projectname") String projectname,
								 @FormParam("projectdescription")String projectdescription,
								 @FormParam("requiredfund") Double requiredfund,
								 @FormParam("projectduration") String projectduration) {
		
		String output = projectObj.insertProject(projectname, projectdescription, requiredfund, projectduration);
		
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProjects(String projectData) {
		
		//convert string to a JSON
		JsonObject obj = new JsonParser().parse(projectData).getAsJsonObject();
		
		//read values
		String id = obj.get("id").getAsString();
		String projectname = obj.get("projectname").getAsString();
		String projectdescription = obj.get("projectdescription").getAsString();
		double requiredfund = obj.get("requiredfund").getAsDouble();
		String projectduration = obj.get("projectduration").getAsString();

		String output = projectObj.updateProjects(id, projectname, projectdescription, requiredfund, projectduration);
		
		return output;
		
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProjects(String projectData) {
		
		//convert string to XML doc
		Document doc = Jsoup.parse(projectData,"",Parser.xmlParser());
		
		//read the value from element
		String id=doc.select("id").text();
		
		String output = projectObj.deleteProjects(id);
		
		return output;
		
		
	}
		
}
	

