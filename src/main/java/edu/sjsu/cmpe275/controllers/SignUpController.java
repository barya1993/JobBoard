package edu.sjsu.cmpe275.controllers;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.sjsu.cmpe275.Util;
import edu.sjsu.cmpe275.model.JobSeeker;
import edu.sjsu.cmpe275.services.SignUpService;

@RestController
public class SignUpController {
	
	@Autowired
	SignUpService signUpService;
	
	@RequestMapping(value="/signUpJobSeeker",method = RequestMethod.POST)
	public <E> ResponseEntity<E> signUpJobSeeker(HttpServletRequest request, HttpServletResponse response) throws JSONException{
		
		URI location;
		
		JSONObject jsonObject = new JSONObject(request.getParameter("data"));
		
		String emailId = jsonObject.getString("email");
		String firstName = jsonObject.getString("firstName");
		String lastName = jsonObject.getString("lastName");
		String selfIntroduction = jsonObject.getString("selfIntroduction");
		String phone = jsonObject.getString("phone");
		String skills = jsonObject.getString("skills");
		//Education education = request.getParameter("education");
		String workExp = jsonObject.getString("workExp");
		String password = jsonObject.getString("password");
		String verificationCode = "";
		Boolean isVerified = false;
		//String profileImagePath = request.getParameter("profileImagePath");
		
		JobSeeker jobseeker = new JobSeeker(firstName, lastName, emailId, selfIntroduction, phone,
				skills, workExp, verificationCode, isVerified, password);
		
		boolean isSignUpSuccessful = signUpService.signUpJobSeeker(jobseeker);
		
		if(!isSignUpSuccessful){
			location = ServletUriComponentsBuilder
		            .fromCurrentServletMapping().path("/applicationError").build().toUri();
		}else{
			location = ServletUriComponentsBuilder
		            .fromCurrentServletMapping().path("/jobSeekerHome").build().toUri();
		}
		
		return Util.redirectTo(location);
		
	}
	
	@RequestMapping(value="/jobSeekerHome",method = RequestMethod.GET)
	public <E> ResponseEntity<E> jobSeekerHome(HttpServletRequest request, HttpServletResponse response){
		
		return null;
	}
}
