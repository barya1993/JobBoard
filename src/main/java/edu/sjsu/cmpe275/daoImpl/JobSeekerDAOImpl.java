package edu.sjsu.cmpe275.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.dao.JobSeekerDAO;
import edu.sjsu.cmpe275.model.JobSeeker;

@Repository
public class JobSeekerDAOImpl implements JobSeekerDAO{

	@PersistenceContext
	protected EntityManager em;
	
	/*@Override
	Transactional*/
	/*public boolean signUpJobSeeker(JobSeeker jobSeeker) {
		try{
			em.persist(jobSeeker);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
*/
	@Override
	public boolean updateProfileJobSeeker(JobSeeker jobSeeker) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getIdByEmailID(String emailId) {
		
		String id = "";
		Query query = em.createQuery("Select j.id from JobSeeker j where j.emailId=:arg1");
		query.setParameter("arg1", emailId);
		try {
			id = (String) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public JobSeeker getJobSeekerByIdAndVerCode(String verificationCode, String jobSeekerId) {
		JobSeeker jobSeeker = null;
		Query query = em.createQuery("Select j from JobSeeker j where j.id=:arg1 and j.verificationCode=:arg2");
		query.setParameter("arg1", jobSeekerId);
		query.setParameter("arg2", verificationCode);
		
		try {
			jobSeeker = (JobSeeker) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return jobSeeker;
	}

	
}
