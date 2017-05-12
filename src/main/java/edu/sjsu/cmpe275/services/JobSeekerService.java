package edu.sjsu.cmpe275.services;

import edu.sjsu.cmpe275.model.JobSeeker;

public interface JobSeekerService {
	public JobSeeker updateJobSeekerProfile(JobSeeker jobSeeker);
	public JobSeeker getJobSeekerProfile(String emailid);
}
