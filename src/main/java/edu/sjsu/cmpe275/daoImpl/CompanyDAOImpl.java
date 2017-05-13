package edu.sjsu.cmpe275.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.dao.CompanyDAO;
import edu.sjsu.cmpe275.model.Company;

@Repository
public class CompanyDAOImpl implements CompanyDAO{
	
	@PersistenceContext
	protected EntityManager em;
	
	@Override
	@Transactional
	public String getIdByEmailID(String emailId) {
		
		String id = "";
		Query query = em.createQuery("Select c.id from Company c where c.email=:arg1");
		query.setParameter("arg1", emailId);
		try {
			id = (String) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	@Override
	@Transactional
	public Company getCompanyByIdAndVerCode(String verificationCode, String companyId) {
		Company company = null;
		Query query = em.createQuery("Select c from Company c where c.companyId=:arg1 and c.verificationCode=:arg2");
		query.setParameter("arg1", companyId);
		query.setParameter("arg2", verificationCode);
		
		try {
			company = (Company) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return company;
	}
}
