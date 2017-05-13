package edu.sjsu.cmpe275.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.model.JobPost;
import edu.sjsu.cmpe275.model.JobSeeker;

@Repository
public interface JobPostDAO {

	public boolean addNewJob(JobPost jobPost);
	
	public JobPost getJobDetails(String jobid);
	
	public boolean updateJobDetails(JobPost jobPost);

	public List<JobPost> getJobsByCompany(String CompanyId);
	
	
}
