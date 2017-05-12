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
import edu.sjsu.cmpe275.model.JobSeeker;
import edu.sjsu.cmpe275.model.ResponseObject;
import edu.sjsu.cmpe275.services.EducationService;
import edu.sjsu.cmpe275.services.SignUpService;

@RestController
public class SignUpController {
	
	@Autowired
	SignUpService signUpService;
	
	@Autowired
	EducationService educationService;
	
	@CrossOrigin(origins = "http://localhost:8000")
	@RequestMapping(value="/signUpJobSeeker",method = RequestMethod.POST)
	public ResponseEntity<?> signUpJobSeeker(HttpServletRequest request, HttpServletResponse response) throws JSONException{
		
		URI location;
		
		/*JSON required from frontend:
		 *{
				"data": {
					"email": "xyz@zbc.com",
					"firstName": "firstname1",
					"lastName": "lastname1",
					"selfIntroduction": "introduction1",
					"phone": "6692459505",
					"skills": "java, python",
					"workExp": "2 years",
					"password": "mypassword",
					"education":[{
						"school": "school1",
						"degree": "degree1",
						"fieldOfStudy": "fieldOfStudy1",
						"gpa": "gpa1"
					},
					{
						"school": "school2",
						"degree": "degree2",
						"fieldOfStudy": "fieldOfStudy2",
						"gpa": "gpa2"
					}]
				}
			}
		 * 
		 */
		
		JSONObject jsonObject = new JSONObject(Util.getDataString(request));
		
		String emailId = jsonObject.getString("email");
		String firstName = jsonObject.getString("firstName");
		String lastName = jsonObject.getString("lastName");
		String selfIntroduction = jsonObject.getString("selfIntroduction");
		String phone = jsonObject.getString("phone");
		String skills = jsonObject.getString("skills");
		
		String workExp = jsonObject.getString("workExp");
		String password = jsonObject.getString("password");
		String verificationCode = "";
		Boolean isVerified = false;
		
		//String profileImagePath = request.getParameter("profileImagePath");
		
		JobSeeker jobseeker = new JobSeeker(firstName, lastName, emailId, selfIntroduction, phone,
				skills, workExp, verificationCode, isVerified, password);
		
		
		
		boolean isSignUpSuccessful = signUpService.signUpJobSeeker(jobseeker);
		
		
		if(!isSignUpSuccessful){
			
			return new ResponseEntity(new JSONObject().toString(),HttpStatus.BAD_REQUEST);
			
		}else{
			/*ObjectMapper mapper = new ObjectMapper();
			String Json =  mapper.writeValueAsString(object);*/
			

			JSONArray jsonArrayEducation = jsonObject.getJSONArray("education");
			List<Education> educationList = new ArrayList<Education>();
			
			for(int i=0 ; i< jsonArrayEducation.length(); i++){
				JSONObject educationJsonObject = jsonArrayEducation.getJSONObject(i);
				Education education = new Education( jobseeker, educationJsonObject.getString("school"), educationJsonObject.getString("degree") , educationJsonObject.getString("fieldOfStudy") , educationJsonObject.getString("gpa"));
				educationList.add(education);
			}
			
			educationService.saveEducation(educationList);
			
			JSONObject returnData = new JSONObject();
			returnData.put("verificationCode", "1234");
			returnData.put("verificationCode1", "12342");
			
			return new ResponseEntity(returnData.toString(),HttpStatus.OK);
		}
		
		
	}
}
