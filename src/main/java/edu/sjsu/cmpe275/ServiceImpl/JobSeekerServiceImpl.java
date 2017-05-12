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
	public String getIdByEmailID(String emailId) {
		
		return jobSeekerDAO.getIdByEmailID(emailId);
	}

	@Override
	public JobSeeker getJobSeekerByIdAndVerCode(String verificationCode, String jobSeekerId) {
		return jobSeekerDAO.getJobSeekerByIdAndVerCode(verificationCode, jobSeekerId);
	}
	
	@Autowired
	JobSeekerDAO jobSeekerDAO;

	@Override
	public JobSeeker updateJobSeekerProfile(JobSeeker jobSeeker) {
		//return null;
		return jobSeekerDAO.updateJobSeekerProfile(jobSeeker);
	}

	@Override
	public JobSeeker getJobSeekerProfile(String emailid) {
		// TODO Auto-generated method stub
		return jobSeekerDAO.getJobSeekerProfile(emailid);
	}

}
