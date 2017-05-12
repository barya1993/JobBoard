package edu.sjsu.cmpe275.dao;

import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.model.JobSeeker;

@Repository
public interface JobSeekerDAO {

	public boolean updateProfileJobSeeker(JobSeeker jobSeeker);
	public String getIdByEmailID(String emailId);
	public JobSeeker getJobSeekerByIdAndVerCode(String verificationCode, String jobSeekerId);
	

	public JobSeeker updateJobSeekerProfile(JobSeeker jobSeeker);
	public JobSeeker getJobSeekerProfile(String emailid);

}
