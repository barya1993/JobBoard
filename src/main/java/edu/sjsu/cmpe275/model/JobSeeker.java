package edu.sjsu.cmpe275.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="JobSeeker")
public class JobSeeker {
	
	/*job_seeker_id
	Email
	first_name
	last_name
	Picture
	Self_introduction
	Education_id
	Work_exp
	Skills
	Verification_code
	is_verified*/
	
	@Id 
	@Column(name="JOB_SEEKER_ID")
	@GeneratedValue(generator="system-uuid") 
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String job_seeker_id;
	
	@Column(name="FIRSTNAME", nullable = false)
	private String firstname;
	@Column(name="LASTNAME", nullable = false)
	private String lastname;
	@Column(name="EMAIL", unique = true, nullable = false)
	private String emailId;
	@Column(name="SELF_INTRODUCTION")
	private String selfIntroduction;
	@Column(name="PHONE", unique = true, nullable = false)
	private String phone; //must be unique
	@Column(name="SKILLS")
	private String skills;
	@Embedded
	private Education education;
	@Column(name="WORKEXP")
	private String workExp;
	@Column(name="VERIFICATION_CODE")
	private String verificationCode;
	@Column(name="IS_VERIFIED")
	private boolean isVerified;
	@Column(name="PROFILE_IMAGE_PATH")
	private String profileImagePath;
	
	
	
	public JobSeeker(String job_seeker_id, String firstname, String lastname, String emailId, String selfIntroduction,
			String phone, String skills, Education education, String workExp, String verificationCode,
			boolean isVerified, String profileImagePath) {
		super();
		this.job_seeker_id = job_seeker_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailId = emailId;
		this.selfIntroduction = selfIntroduction;
		this.phone = phone;
		this.skills = skills;
		this.education = education;
		this.workExp = workExp;
		this.verificationCode = verificationCode;
		this.isVerified = isVerified;
		this.profileImagePath = profileImagePath;
	}
	
	public JobSeeker(){
		
	}
	
	public String getJob_seeker_id() {
		return job_seeker_id;
	}
	public void setJob_seeker_id(String job_seeker_id) {
		this.job_seeker_id = job_seeker_id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getSelfIntroduction() {
		return selfIntroduction;
	}
	public void setSelfIntroduction(String selfIntroduction) {
		this.selfIntroduction = selfIntroduction;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public Education getEducation() {
		return education;
	}
	public void setEducation(Education education) {
		this.education = education;
	}
	public String getWorkExp() {
		return workExp;
	}
	public void setWorkExp(String workExp) {
		this.workExp = workExp;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public boolean isVerified() {
		return isVerified;
	}
	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	public String getProfileImagePath() {
		return profileImagePath;
	}
	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}
	
	
}
