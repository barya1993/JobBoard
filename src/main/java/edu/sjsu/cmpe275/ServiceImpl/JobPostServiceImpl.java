package edu.sjsu.cmpe275.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.dao.JobPostDAO;

import edu.sjsu.cmpe275.dao.JobSeekerDAO;
import edu.sjsu.cmpe275.model.Application;
import edu.sjsu.cmpe275.model.JobPost;
import edu.sjsu.cmpe275.model.JobSeeker;
import edu.sjsu.cmpe275.services.JobPostService;
import edu.sjsu.cmpe275.services.JobSeekerService;

@Service
public class JobPostServiceImpl implements JobPostService{

	@Autowired
	JobPostDAO jobPostDAO;

	@Override
	public List<Application> getJobPostApplications(JobPost jobPost) {
		// TODO Auto-generated method stub
		return jobPostDAO.getJobPostApplications(jobPost);
	}

	@Override
	public List<JobPost> searchByText(String keyword) {
		return jobPostDAO.searchByText(keyword);
	}
	
}
