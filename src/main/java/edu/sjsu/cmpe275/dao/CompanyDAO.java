package edu.sjsu.cmpe275.dao;

import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.model.Company;

@Repository
public interface CompanyDAO {
	
	public String getIdByEmailID(String emailId);
	public Company getCompanyByIdAndVerCode(String verificationCode, String companyId);
	
}
