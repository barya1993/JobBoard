package edu.sjsu.cmpe275.services;

import org.springframework.stereotype.Service;


import edu.sjsu.cmpe275.model.JobPost;

import edu.sjsu.cmpe275.model.JobSeeker;

@Service
public interface CompanyService {

	public boolean addNewJob(JobPost jobPost);

	public JobPost getJobDetails(String jobid);
}
