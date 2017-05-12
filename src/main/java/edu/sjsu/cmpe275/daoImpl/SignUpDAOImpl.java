package edu.sjsu.cmpe275.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.dao.SignUpDAO;
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
		
		JobSeeker jobSeeker = null;
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
}
