package edu.sjsu.cmpe275.dao;

import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.model.JobSeeker;

@Repository
public interface JobSeekerDAO {
	public JobSeeker updateJobSeekerProfile(JobSeeker jobSeeker);
}
