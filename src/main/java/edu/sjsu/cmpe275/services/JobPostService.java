package edu.sjsu.cmpe275.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.model.Application;
import edu.sjsu.cmpe275.model.JobPost;

@Service
@Transactional
public interface JobPostService {
	public List<Application> getJobPostApplications(JobPost jobPost);
}
