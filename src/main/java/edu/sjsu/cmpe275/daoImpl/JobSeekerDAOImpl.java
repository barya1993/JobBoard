package edu.sjsu.cmpe275.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.dao.JobSeekerDAO;
import edu.sjsu.cmpe275.model.Application;
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
	
	@Override
	@Transactional
	public boolean applyToJobPost(Application application){
		try{
			em.persist(application);
			return true;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getJobSeekerApplications(JobSeeker jobSeekerId){
		
		List<Application> returnObj = null;
		
		Query query = em.createQuery("Select j from Application j where j.jobSeekerId=:arg1");
		query.setParameter("arg1", jobSeekerId);
		
		try {
			returnObj = (List<Application>) query.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return returnObj;
		
	}

	
}
