package edu.sjsu.cmpe275.services;

import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.model.JobSeeker;

@Service
public interface JobSeekerService {
	public JobSeeker updateJobSeekerProfile(JobSeeker jobSeeker);

	public boolean applyToJobPost(String jobSeekerId, String jobPostId);

	public JobSeeker getJobSeekerProfile(String emailid);

}
