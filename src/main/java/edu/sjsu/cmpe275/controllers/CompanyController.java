package edu.sjsu.cmpe275.controllers;

import java.io.BufferedReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.sjsu.cmpe275.Util;
import edu.sjsu.cmpe275.model.Education;
import edu.sjsu.cmpe275.model.JobPost;
import edu.sjsu.cmpe275.model.JobSeeker;
import edu.sjsu.cmpe275.model.ResponseObject;
import edu.sjsu.cmpe275.services.CompanyService;
import edu.sjsu.cmpe275.services.EducationService;
import edu.sjsu.cmpe275.services.SignUpService;

@RestController
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	@RequestMapping(value="/addNewJob",method = RequestMethod.POST)
	public ResponseEntity<?> addNewJob(HttpServletRequest request, HttpServletResponse response) throws JSONException
	{
		JSONObject jsonObject = new JSONObject(Util.getDataString(request));
		
		//Dummy company_id, will be replaced by session_id later
		//String companyId = "1";
		
		String companyId = jsonObject.getString("companyId");
		String title = jsonObject.getString("title");
		String description = jsonObject.getString("description");
		String office_location = jsonObject.getString("office_location");
		String responsibilities = jsonObject.getString("responsibilities");
		String salary = jsonObject.getString("salary");
		
		JobPost jobPost = new JobPost(companyId, title, description, office_location, responsibilities, salary);
		
		boolean isJobAddingSuccessful = companyService.addNewJob(jobPost);
		
		
		if(!isJobAddingSuccessful){
			
			return new ResponseEntity("Failure",HttpStatus.BAD_REQUEST);
			
		}
		else
		{
			return new ResponseEntity("Success",HttpStatus.OK);
		}
		
	}
}
