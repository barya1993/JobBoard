package edu.sjsu.cmpe275.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.dao.CompanyDAO;

import edu.sjsu.cmpe275.dao.SignUpDAO;
import edu.sjsu.cmpe275.model.Company;
import edu.sjsu.cmpe275.model.JobPost;
import edu.sjsu.cmpe275.model.JobSeeker;

@Repository
public class CompanyDAOImpl implements CompanyDAO{

	@PersistenceContext
	protected EntityManager em;
	
	@Override
	@Transactional
	public String getIdByEmailID(String emailId) {
		
		String id = "";
		Query query = em.createQuery("Select c.id from Company c where c.email=:arg1");
		query.setParameter("arg1", emailId);
		try {
			id = (String) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	@Override
	@Transactional
	public Company getCompanyByIdAndVerCode(String verificationCode, String companyId) {
		Company company = null;
		Query query = em.createQuery("Select c from Company c where c.companyId=:arg1 and c.verificationCode=:arg2");
		query.setParameter("arg1", companyId);
		query.setParameter("arg2", verificationCode);
		
		try {
			company = (Company) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return company;
	}

	public List<Company> getAllCompanies() {
		
		Query query = em.createQuery("SELECT p FROM Company p ORDER BY p.id");
		
		try {
			
			@SuppressWarnings("unchecked")
			List<Company> companies = query.getResultList();
			System.out.println("company "+companies.get(0));
			
			return companies;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public Company findCompanyById(String companyId) {	
		try 
		{
			Company company = em.find(Company.class, companyId);
			return company;
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		
		
	}

}
