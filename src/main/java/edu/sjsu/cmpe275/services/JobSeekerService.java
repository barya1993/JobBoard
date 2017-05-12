package edu.sjsu.cmpe275.services;

import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.model.JobSeeker;

@Service
public interface JobSeekerService {
	public String getIdByEmailID(String emailId);
	public JobSeeker getJobSeekerByIdAndVerCode(String verificationCode, String jobSeekerId);
}
