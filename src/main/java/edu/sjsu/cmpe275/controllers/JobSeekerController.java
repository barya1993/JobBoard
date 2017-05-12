package edu.sjsu.cmpe275.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.Util;
import edu.sjsu.cmpe275.services.JobSeekerService;

@RestController
public class JobSeekerController {
	
	@Autowired
	JobSeekerService jobSeekerService;
	
	public ResponseEntity<?> updateJobSeekerProfile(HttpServletRequest request, HttpServletResponse response) throws JSONException{
	
	
	JSONObject jsonObject = new JSONObject(Util.getDataString(request));
	return null;

	}
}
