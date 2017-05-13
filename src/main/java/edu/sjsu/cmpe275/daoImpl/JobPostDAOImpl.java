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
import edu.sjsu.cmpe275.dao.JobPostDAO;
import edu.sjsu.cmpe275.dao.SignUpDAO;
import edu.sjsu.cmpe275.model.Application;
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

	@Override
	@Transactional
	public JobPost getJobDetails(String jobid) {
		
		try{
			JobPost jobPost = em.find(JobPost.class, jobid);
			System.out.println("jobPost1 "+jobPost.getTitle());
			return jobPost;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	@Transactional
	public boolean updateJobDetails(JobPost jobPost) {
		
		try {
			System.out.println("inside "+jobPost.getTitle());
			em.merge(jobPost);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	@Transactional
	public List<JobPost> getJobsByCompany(String CompanyId) {
		
		Query query = em.createQuery("SELECT p FROM JobPost p ORDER BY p.id");
		
		try {
			
			List<JobPost> resultList = new ArrayList<JobPost>();
			
			@SuppressWarnings("unchecked")
			List<JobPost> jobPosts = query.getResultList();
			
			for(int i = 0 ; i < jobPosts.size() ; i++)
			{
				if(jobPosts.get(i).getCompanyId().equalsIgnoreCase(CompanyId))
				{
					resultList.add(jobPosts.get(i));
				}
			}
		
			return resultList;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	public List<Application> getJobPostApplications(JobPost jobPost) {

List<Application> returnObj = null;
		
		Query query = em.createQuery("Select j from Application j where j.jobPostId=:arg1");
		query.setParameter("arg1", jobPost);
		
		try {
			returnObj = (List<Application>) query.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return returnObj;
		
	}

	@Override
	public List<JobPost> searchByText(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
