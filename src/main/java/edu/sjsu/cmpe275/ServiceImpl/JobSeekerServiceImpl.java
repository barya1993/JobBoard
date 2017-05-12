package edu.sjsu.cmpe275.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.dao.JobSeekerDAO;
import edu.sjsu.cmpe275.model.JobSeeker;
import edu.sjsu.cmpe275.services.JobSeekerService;

@Service
public class JobSeekerServiceImpl implements JobSeekerService {
	
	@Autowired
	JobSeekerDAO jobSeekerDAO;

	@Override
	public JobSeeker updateJobSeekerProfile(JobSeeker jobSeeker) {
		//return null;
		return jobSeekerDAO.updateJobSeekerProfile(jobSeeker);
	}

	@Override
	public boolean applyToJobPost(String jobSeekerId, String jobPostId){
		
		
		
		return true;
	}
}
