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
import edu.sjsu.cmpe275.model.Application;
import edu.sjsu.cmpe275.model.JobPost;
import edu.sjsu.cmpe275.model.JobSeeker;
import edu.sjsu.cmpe275.services.CompanyService;
import edu.sjsu.cmpe275.services.JobSeekerService;

@RestController
public class JobSeekerController {
	
	@Autowired
	JobSeekerService jobSeekerService;
	
	@Autowired
	CompanyService companyService;
	
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
		returnData.put("firstName", updatedSeeker.getFirstName());
		returnData.put("lastName", updatedSeeker.getLastName());
		returnData.put("selfIntroduction", updatedSeeker.getSelfIntroduction());
		returnData.put("phone", updatedSeeker.getPhone());
		returnData.put("skills", updatedSeeker.getSkills());
		returnData.put("workExp", updatedSeeker.getWorkExp());
		return new ResponseEntity(returnData.toString(),HttpStatus.OK);

	}
	
	
	@CrossOrigin(origins = "http://localhost:8000")
	@RequestMapping(value="/applyToJobPost",method = RequestMethod.POST)
	public ResponseEntity<?> applyToJobPost(HttpServletRequest request, HttpServletResponse response) throws JSONException{
		
		JSONObject jsonObject = new JSONObject(Util.getDataString(request));
		
		String jobPostId = jsonObject.getString("jobPostId");
		//String jobSeekerId = session.get;
		String jobSeekerEmailId = "xyz2@zbc.com";
		String resumeOrProfile = jsonObject.getString("applyWithResumeOrProfile");
		String resume;
		if(resumeOrProfile.equals("Resume"))
			resume = jsonObject.getString("resume");
		else
			resume = null;
		
		
		JobSeeker jobSeeker = jobSeekerService.getJobSeekerProfile(jobSeekerEmailId);
		JobPost jobPost = companyService.getJobDetails(jobPostId);
		
		Application newApplication = new Application(jobPost, jobSeeker, resume, "NEW");
		
		if(jobSeekerService.applyToJobPost(newApplication))
		{
			return new ResponseEntity("Application Submitted",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity("Error in Applying for the desired Job Post",HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	
	
	
	
	
	
	
}
