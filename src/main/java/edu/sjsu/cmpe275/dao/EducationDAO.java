package edu.sjsu.cmpe275.dao;

import org.springframework.stereotype.Repository;

import edu.sjsu.cmpe275.model.Education;

@Repository
public interface EducationDAO {
	public boolean saveEducation(Education edcuation);
}
