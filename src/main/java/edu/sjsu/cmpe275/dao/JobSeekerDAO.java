package edu.sjsu.cmpe275.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.model.Application;
import edu.sjsu.cmpe275.model.JobSeeker;

@Repository
public interface JobSeekerDAO {

	public boolean updateProfileJobSeeker(JobSeeker jobSeeker);
	public String getIdByEmailID(String emailId);
	public JobSeeker getJobSeekerByIdAndVerCode(String verificationCode, String jobSeekerId);
	

	public JobSeeker updateJobSeekerProfile(JobSeeker jobSeeker);

	public boolean applyToJobPost(Application application);

	public JobSeeker getJobSeekerProfile(String emailid);
	
	public List<Application> getJobSeekerApplications(JobSeeker jobSeekerId);

}
