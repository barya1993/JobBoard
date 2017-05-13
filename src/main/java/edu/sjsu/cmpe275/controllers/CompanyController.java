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
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value="/addJobByCompany",method = RequestMethod.POST)
	public ResponseEntity<?> addJobByCompany(HttpServletRequest request, HttpServletResponse response) throws JSONException
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
	
	
	@RequestMapping(value="/retrieveJobById",method = RequestMethod.POST)
	public ResponseEntity<?> retrieveJobById(HttpServletRequest request, HttpServletResponse response) throws JSONException
	{
		
		JSONObject jsonObject1 = new JSONObject(Util.getDataString(request));
		
		String jobid = jsonObject1.getString("jobid");
		
		JobPost jobPost = companyService.getJobDetails(jobid);
		
		System.out.println("jobPost "+jobPost);
		
		if(jobPost !=null)
		{
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put("job_post_id", jobPost.getJobPostId());
			jsonObject.put("company_id", jobPost.getCompanyId());
			jsonObject.put("description", jobPost.getDescription());
			jsonObject.put("office_location", jobPost.getOfficeLocation());
			jsonObject.put("responsibilities", jobPost.getResponsibilities());
			jsonObject.put("salary", jobPost.getSalary());
			jsonObject.put("title", jobPost.getTitle());
			
			return new ResponseEntity(jsonObject.toString(),HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity("No job found",HttpStatus.BAD_REQUEST);
		}	
	}
	
	
	
	
	
	
	@RequestMapping(value="/updateJobByCompany",method = RequestMethod.POST)
	public ResponseEntity<?> updateJobByCompany(HttpServletRequest request, HttpServletResponse response) throws JSONException
	{
		JSONObject jsonObject = new JSONObject(Util.getDataString(request));
		
		//Dummy company_id, will be replaced by session_id later
		//String companyId = "1";
		String companyId = jsonObject.getString("companyId");
		String jobId = jsonObject.getString("jobId");
		String title = jsonObject.getString("title");
		String description = jsonObject.getString("description");
		String office_location = jsonObject.getString("office_location");
		String responsibilities = jsonObject.getString("responsibilities");
		String salary = jsonObject.getString("salary");
		
		JobPost jobPost = companyService.getJobDetails(jobId);
		
		if(jobPost != null){
			
			jobPost.setCompanyId(companyId);
			jobPost.setDescription(description);
			jobPost.setOfficeLocation(office_location);
			jobPost.setResponsibilities(responsibilities);
			jobPost.setSalary(salary);
			jobPost.setTitle(title);
			
			Boolean successFlag = companyService.updateJobDetails(jobPost);
			
			if(successFlag)
			{
				return new ResponseEntity("Updated successfully",HttpStatus.OK);
				
				//need to notify all the applicants
			}
			else
			{
				return new ResponseEntity("Bad request",HttpStatus.BAD_REQUEST);
			}
		}
		else
		{
			return new ResponseEntity("Not found",HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
	@RequestMapping(value="/findJobsByCompany",method = RequestMethod.POST)
	public ResponseEntity<?> findAllJobsOfCompany(HttpServletRequest request, HttpServletResponse response) throws JSONException
	{
		JSONObject jsonObject = new JSONObject(Util.getDataString(request));
		
		//Dummy company_id, will be replaced by session_id later
		//String companyId = "1";
		String companyId = jsonObject.getString("companyId");
		
		List<JobPost> jobPosts = companyService.getJobsByCompany(companyId);
		
		if(jobPosts.size() != 0)
		{
			JSONObject jsonObject1 = new JSONObject();
			JSONArray jsonArray = new JSONArray(jobPosts);
			jsonObject1.put("Jobs", jsonArray);
			return new ResponseEntity(jsonObject1.toString(),HttpStatus.OK);
		}
		
		else
		{
			return new ResponseEntity("No jobs",HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	

}
