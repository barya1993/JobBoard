package edu.sjsu.cmpe275.daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.dao.EducationDAO;
import edu.sjsu.cmpe275.model.Education;

@Repository
public class EducationDAOImpl implements EducationDAO {
	@PersistenceContext
	protected EntityManager em;
	
	@Override
	@Transactional
	public boolean saveEducation(Education edcuation){
		try{
			em.persist(edcuation);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
