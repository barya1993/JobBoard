package edu.sjsu.cmpe275.services;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.model.Application;
import edu.sjsu.cmpe275.model.JobSeeker;


@Service
public interface JobSeekerService {
	public JobSeeker updateJobSeekerProfile(JobSeeker jobSeeker);

	public boolean applyToJobPost(Application application);

	public JobSeeker getJobSeekerProfile(String emailid);

  public String getIdByEmailID(String emailId);
	public JobSeeker getJobSeekerByIdAndVerCode(String verificationCode, String jobSeekerId);


}
