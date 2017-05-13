package edu.sjsu.cmpe275.services;

import java.util.List;

import org.springframework.stereotype.Service;


import edu.sjsu.cmpe275.model.JobPost;

import edu.sjsu.cmpe275.model.JobSeeker;

@Service
public interface CompanyService {

	public boolean addNewJob(JobPost jobPost);

	public JobPost getJobDetails(String jobid);
	
	public boolean updateJobDetails(JobPost jobPost);
	
	public List<JobPost> getJobsByCompany(String CompanyId);
}
