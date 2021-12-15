package org.upec.m2.fa.rest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.upec.dao.Professeur;
import org.upec.dao.ReadXML;
// import org.upec.dao.User;
// import org.upec.dao.UserDao;


@Path("/api")
public class APIExposer {
	
	@Path("/professeurs")
	@GET
	@Produces("text/json")
	public Response getProfesseurs() throws JSONException, SQLException {
		
		//get the list of users
		ReadXML xmlReader = new ReadXML();
		ArrayList<Professeur> profs = (ArrayList<Professeur>) xmlReader.getAllProfs(true);
		
		//Convert users to a JSON object
		JSONArray jsonProfs = new JSONArray();
		
		for(int i = 0; i < profs.size(); i++) {
			Professeur p = profs.get(i);
			JSONObject jsonProf = new JSONObject();
			jsonProf.put("num_p", p.getNum_prof());
			jsonProf.put("id_p", p.getId_person());
			jsonProf.put("diplome", p.getDiplome());
			jsonProf.put("module", p.getModule());

			jsonProfs.put(jsonProf);
			
		}
		
		String result = "" + jsonProfs;
		return Response.status(200).entity(result).build();
	}

// @Path("/api")
// public class APIExposer {
	
// 	@Path("/users")
// 	@GET
// 	@Produces("text/json")
// 	public Response getUsers() throws JSONException {
		
// 		//get the list of users
// 		UserDao uDao = new UserDao();
// 		ArrayList<User> users = (ArrayList<User>) uDao.getAllUsers();
		
// 		//Convert users to a JSON object
// 		JSONArray jsonUsers = new JSONArray();
		
// 		for(int i = 0; i < users.size(); i++) {
// 			User u = users.get(i);
// 			JSONObject jsonUser = new JSONObject();
// 			jsonUser.put("id", u.getId());
// 			jsonUser.put("name", u.getName());
// 			jsonUser.put("profession", u.getProfession());
			
// 			jsonUsers.put(jsonUser);
			
// 		}
		
// 		String result = "" + jsonUsers;
// 		return Response.status(200).entity(result).build();
// 	}

// 	@Path("/users")
// 	@POST	
// 	@Consumes("application/json")
// 	@Produces("application/json")	
// 	public Response addUser(InputStream jUserStream) {
		
// 		JSONParser jsonParser = new JSONParser();
// 		org.json.simple.JSONObject jsonObject;
// 		try {
// 			jsonObject = (org.json.simple.JSONObject)jsonParser.parse(
// 			      new InputStreamReader(jUserStream, "UTF-8"));
			
// 			User u = new User(Integer.parseInt((String)jsonObject.get("id")), (String)jsonObject.get("name"), (String)jsonObject.get("profession"));
			
// 			UserDao uDao = new UserDao();
// 			uDao.addUser(u);
			
// 		} catch (UnsupportedEncodingException e) {
// 			// TODO Auto-generated catch block
// 			e.printStackTrace();
// 		} catch (IOException e) {
// 			// TODO Auto-generated catch block
// 			e.printStackTrace();
// 		} catch (ParseException e) {
// 			// TODO Auto-generated catch block
// 			e.printStackTrace();
// 		}
						
// 		String result = "{'success':1}" ;
// 		return Response.status(200).entity(result).build();		
// 	}
	
// 	@GET
// 	@Path("/users/{userid}")
// 	@Produces("application/json")
// 	public Response getUser(@PathParam("userid") int userid){
// 		//get the list of users
// 		UserDao uDao = new UserDao();
		
// 		User u = uDao.getUser(userid);
		
// 		JSONObject jsonUser = new JSONObject();
// 		jsonUser.put("id", u.getId());
// 		jsonUser.put("name", u.getName());
// 		jsonUser.put("profession", u.getProfession());
		
// 		String result = "" + jsonUser;
// 		return Response.status(200).entity(result).build();
// 	}
}
