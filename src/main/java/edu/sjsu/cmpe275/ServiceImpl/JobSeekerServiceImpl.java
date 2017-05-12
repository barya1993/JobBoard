package edu.sjsu.cmpe275.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.dao.SignUpDAO;
import edu.sjsu.cmpe275.model.JobSeeker;
import edu.sjsu.cmpe275.services.SignUpService;

@Service
public class JobSeekerServiceImpl implements SignUpService {
	
	@Autowired
	SignUpDAO signUpDAO;

	@Override
	public boolean signUpJobSeeker(JobSeeker jobSeeker) {
		
		return signUpDAO.signUpJobSeeker(jobSeeker);
	}

}
