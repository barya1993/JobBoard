package edu.sjsu.cmpe275.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Education {

/*	School
	Degree
	field_of_study	
		gpa*/
	
	
	@Column(name="SCHOOL")
	private String school;
	
	@Column(name="DEGREE")
	private String degree;
	
	@Column(name="FIELD_OF_STUDY")
	private String fieldOfStudy;
	
	@Column(name="GPA")
	private String gpa;
	
	
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getFieldOfStudy() {
		return fieldOfStudy;
	}
	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
	public String getGpa() {
		return gpa;
	}
	public void setGpa(String gpa) {
		this.gpa = gpa;
	}
	
	
	
	
}
