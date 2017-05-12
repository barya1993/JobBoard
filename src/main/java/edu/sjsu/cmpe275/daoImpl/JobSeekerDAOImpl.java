package edu.sjsu.cmpe275.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.dao.JobSeekerDAO;

import edu.sjsu.cmpe275.model.JobSeeker;

@Repository
public class JobSeekerDAOImpl implements JobSeekerDAO{

	@PersistenceContext
	protected EntityManager em;

	@Override
	@Transactional
	public JobSeeker updateJobSeekerProfile(JobSeeker jobSeeker) {
		// TODO Auto-generated method stub
		try {
			em.merge(jobSeeker);
			return em.find(jobSeeker.getClass(),jobSeeker.getEmailId());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	@Override
	@Transactional
	public boolean applyToJobPost(String jobSeekerId, String jobPostId){
		try{
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
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
	
}
