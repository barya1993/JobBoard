package edu.sjsu.cmpe275.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.dao.SignUpDAO;
import edu.sjsu.cmpe275.model.Company;
import edu.sjsu.cmpe275.model.JobSeeker;

@Repository
public class SignUpDAOImpl implements SignUpDAO{

	@PersistenceContext
	protected EntityManager em;
	
	@Override
	@Transactional
	public boolean signUpJobSeeker(JobSeeker jobSeeker) {
		try{
			em.persist(jobSeeker);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	@Transactional
	public boolean updateVerifyJobSeeker(String emailId, String verifyStatus) {
		
		Query query = em.createQuery("update JobSeeker j set j.isVerified=:arg1 where j.emailId=:arg2");
		query.setParameter("arg1", verifyStatus);
		query.setParameter("arg2", emailId);
		
		try {
			query.executeUpdate();
			
		} catch (NoResultException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public boolean checkLoginCredentialsCompany(String emailId, String password) {
		Company c = null;
		Query query = em.createQuery("Select c from Company c where c.email=:arg1 and c.password=:arg2 and c.isVerified=:arg3");
		query.setParameter("arg1", emailId);
		query.setParameter("arg2", password);
		query.setParameter("arg3", "true");
		
		try {
			c = (Company) query.getSingleResult();
			if(c == null){
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	@Transactional
	public boolean checkLoginCredentialsJobSeeker(String emailId, String password) {
		JobSeeker j = null;
		Query query = em.createQuery("Select j from JobSeeker j where j.emailId=:arg1 and j.password=:arg2 and j.isVerified=:arg3");
		query.setParameter("arg1", emailId);
		query.setParameter("arg2", password);
		query.setParameter("arg3", "true");
		
		try {
			j = (JobSeeker) query.getSingleResult();
			if(j == null){
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	
}
