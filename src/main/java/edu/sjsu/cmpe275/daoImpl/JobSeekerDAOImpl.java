package edu.sjsu.cmpe275.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
			System.out.println("inside updatejobseeker"+jobSeeker.getEmailId());
			em.merge(jobSeeker);
			//JobSeeker j = new JobSeeker();
			return getJobSeekerProfile(jobSeeker.getEmailId());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public JobSeeker getJobSeekerProfile(String emailid) {
		// TODO Auto-generated method stub
		try {
			//JobSeeker jobSeeker = new JobSeeker();
			List<JobSeeker> jobSeekerList = new ArrayList<JobSeeker>();
			Query query2 = em.createQuery("Select m from JobSeeker m where m.emailId=:arg1");
			query2.setParameter("arg1", emailid);
			jobSeekerList = query2.getResultList();
			System.out.println(jobSeekerList);
			return jobSeekerList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
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
