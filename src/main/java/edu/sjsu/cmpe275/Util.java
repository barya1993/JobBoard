package edu.sjsu.cmpe275;
import java.net.URI;

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
	
}
