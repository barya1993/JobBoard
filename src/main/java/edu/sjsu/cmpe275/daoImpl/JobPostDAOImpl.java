package edu.sjsu.cmpe275.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.dao.CompanyDAO;
import edu.sjsu.cmpe275.dao.JobPostDAO;
import edu.sjsu.cmpe275.dao.SignUpDAO;
import edu.sjsu.cmpe275.model.JobPost;
import edu.sjsu.cmpe275.model.JobSeeker;

@Repository
public class JobPostDAOImpl implements JobPostDAO{

	@PersistenceContext
	protected EntityManager em;
	
	@Override
	@Transactional
	public boolean addNewJob(JobPost jobPost) {
		
		try{
			em.persist(jobPost);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	

}
