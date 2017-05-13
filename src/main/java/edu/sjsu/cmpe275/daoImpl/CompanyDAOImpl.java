package edu.sjsu.cmpe275.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
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

}
