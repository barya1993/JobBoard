package edu.sjsu.cmpe275.services;

import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.model.Company;
import edu.sjsu.cmpe275.model.JobPost;

@Service
public interface CompanyService {

	public boolean addNewJob(JobPost jobPost);

	public JobPost getJobDetails(String jobid);
	public String getIdByEmailID(String emailId);
	public Company getCompanyByIdAndVerCode(String verificationCode, String companyId);
}
