package edu.sjsu.cmpe275.controllers;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.Util;
import edu.sjsu.cmpe275.model.JobSeeker;
import edu.sjsu.cmpe275.services.JobSeekerService;

@RestController
public class JobSeekerController {
	
	@Autowired
	JobSeekerService jobSeekerService;
	
	@CrossOrigin(origins = "http://localhost:8000")
	@RequestMapping(value="/updateJobSeekerProfile",method = RequestMethod.POST)
	public ResponseEntity<?> updateJobSeekerProfile(HttpServletRequest request, HttpServletResponse response) throws JSONException{
		URI location;
		
		JSONObject jsonObject = new JSONObject(Util.getDataString(request));
		
		String emailId = jsonObject.getString("email");
		String firstName = jsonObject.getString("firstName");
		String lastName = jsonObject.getString("lastName");
		String selfIntroduction = jsonObject.getString("selfIntroduction");
		String phone = jsonObject.getString("phone");
		String skills = jsonObject.getString("skills");
		
		String workExp = jsonObject.getString("workExp");
		
		System.out.println(emailId);
		JobSeeker jobseeker = jobSeekerService.getJobSeekerProfile(emailId);
		System.out.println(jobseeker.getFirstName());
		jobseeker.setFirstName(firstName);
		jobseeker.setLastName(lastName);
		jobseeker.setPhone(phone);
		jobseeker.setSelfIntroduction(selfIntroduction);
		jobseeker.setSkills(skills);
		
		JobSeeker updatedSeeker = 
				jobSeekerService.updateJobSeekerProfile(jobseeker);
		System.out.println(updatedSeeker.getEmailId());
		JSONObject returnData = new JSONObject();
		
		returnData.put("email", updatedSeeker.getEmailId());
		
		
		return new ResponseEntity(returnData.toString(),HttpStatus.OK);

	}
}
