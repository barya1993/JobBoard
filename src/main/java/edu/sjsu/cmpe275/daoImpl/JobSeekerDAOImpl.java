package edu.sjsu.cmpe275.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.dao.JobSeekerDAO;
import edu.sjsu.cmpe275.dao.SignUpDAO;
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
}
