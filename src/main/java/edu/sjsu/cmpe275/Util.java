package edu.sjsu.cmpe275;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {
	
	final static String BASE_URL = "http://localhost:8080";
	
	public static <E> ResponseEntity<E> redirectTo(URI location){
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return (ResponseEntity<E>) new ResponseEntity<Void>(headers, HttpStatus.MOVED_PERMANENTLY);
	}
	
	public static String getDataString(HttpServletRequest request){
		StringBuffer jb = new StringBuffer();
		JSONObject dataJson = new JSONObject();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		    dataJson = new JSONObject(jb.toString());
		    
		  } catch (Exception e) { /*report an error*/ }
		  
		  return dataJson.get("data").toString();
	} 
	
}
