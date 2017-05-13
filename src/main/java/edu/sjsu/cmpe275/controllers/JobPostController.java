package edu.sjsu.cmpe275.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.Util;
import edu.sjsu.cmpe275.model.JobPost;
import edu.sjsu.cmpe275.services.JobPostService;

@RestController
public class JobPostController {
	
	@Autowired
	JobPostService jobPostService;

	@RequestMapping(value="/searchByText",method = RequestMethod.POST)
	public ResponseEntity<?> searchByText(HttpServletRequest request, HttpServletResponse response) throws JSONException
	{
		JSONObject jsonObject = new JSONObject(Util.getDataString(request));
		
		String keyword = jsonObject.getString("keyword");
		
		List<JobPost> jobPostList = null;
		if(keyword != null && !"".equalsIgnoreCase(keyword))
			jobPostList = jobPostService.searchByText(keyword);
		
		JSONObject returnJsonObject = new JSONObject();
		JSONArray jsonArray = new JSONArray(jobPostList);
		returnJsonObject.put("Response", jsonArray);
		
		return new ResponseEntity(returnJsonObject.toString(),HttpStatus.OK);
	}
}
