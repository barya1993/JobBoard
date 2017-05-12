package edu.sjsu.cmpe275.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="JobPost")
public class JobPost {

	/*job_post_id
	Company_id
	Title
	Description
	Responsibilities
	Office_location
	Salary*/
	
	@Id 
	@Column(name="JOB_POST_ID")
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String jobPostId;
	
	@Column(name="COMPANY_ID")
	private String companyId;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="RESPONSIBILITIES")
	private String responsibilities;
	
	@Column(name="OFFICE_LOCATION")
	private String officeLocation;
	
	@Column(name="SALARY")
	private String salary;
		
	
	public JobPost(String companyId, String title, String description, String responsibilities,
			String officeLocation, String salary) {
		super();
		this.companyId = companyId;
		this.title = title;
		this.description = description;
		this.responsibilities = responsibilities;
		this.officeLocation = officeLocation;
		this.salary = salary;
	}
	
	public JobPost()
	{
		
	}
	
	public String getJobPostId() {
		return jobPostId;
	}
	public void setJobPostId(String jobPostId) {
		this.jobPostId = jobPostId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getResponsibilities() {
		return responsibilities;
	}
	public void setResponsibilities(String responsibilities) {
		this.responsibilities = responsibilities;
	}
	public String getOfficeLocation() {
		return officeLocation;
	}
	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	
	
}
