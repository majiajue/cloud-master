package com.datababys.entity.main;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.datababys.entity.Idable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



@Entity
@Table(name = "sys_staff")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "net.hp.es.adm.healthcare.rphcp.entity.main.Staff")
public class Staff implements Idable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String gbcode;
	private String zonegb;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organcode")
	private Organization organization;
	private String idcard;
	private String name;
	private String sex;
	private Date birthday;
	private String mobile;
	private String nationcode;
	private String homeAddr;
	private String homeZip;
	private String homeTel;
	private String mail;
	private String university;
	private Date graduateDate;
	private String specialityText;
	private String education;
	private String degree;
	private String party;
	private String marriage;
	private String officeTel;
	private String officeFax;
	private String technical;
	private String technicalText;
	private String business;
	private String businessText;
	private Date startWorkDate;
	private Date orgWorkDate;
	private String work;
	private String workCardId;
	private String cardNum;
	private String type;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "depId")
	private Department department;
	private String status;

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGbcode() {
		return this.gbcode;
	}

	public void setGbcode(String gbcode) {
		this.gbcode = gbcode;
	}

	public String getZonegb() {
		return this.zonegb;
	}

	public void setZonegb(String zonegb) {
		this.zonegb = zonegb;
	}

	public String getIdcard() {
		return this.idcard;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNationcode() {
		return this.nationcode;
	}

	public void setNationcode(String nationcode) {
		this.nationcode = nationcode;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUniversity() {
		return this.university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getParty() {
		return this.party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getMarriage() {
		return this.marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getTechnical() {
		return this.technical;
	}

	public void setTechnical(String technical) {
		this.technical = technical;
	}

	public String getBusiness() {
		return this.business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getWork() {
		return this.work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHomeAddr() {
		return this.homeAddr;
	}

	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}

	public String getHomeZip() {
		return this.homeZip;
	}

	public void setHomeZip(String homeZip) {
		this.homeZip = homeZip;
	}

	public String getHomeTel() {
		return this.homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public Date getGraduateDate() {
		return this.graduateDate;
	}

	public void setGraduateDate(Date graduateDate) {
		this.graduateDate = graduateDate;
	}

	public String getSpecialityText() {
		return this.specialityText;
	}

	public void setSpecialityText(String specialityText) {
		this.specialityText = specialityText;
	}

	public String getOfficeTel() {
		return this.officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getOfficeFax() {
		return this.officeFax;
	}

	public void setOfficeFax(String officeFax) {
		this.officeFax = officeFax;
	}

	public String getTechnicalText() {
		return this.technicalText;
	}

	public void setTechnicalText(String technicalText) {
		this.technicalText = technicalText;
	}

	public String getBusinessText() {
		return this.businessText;
	}

	public void setBusinessText(String businessText) {
		this.businessText = businessText;
	}

	public Date getStartWorkDate() {
		return this.startWorkDate;
	}

	public void setStartWorkDate(Date startWorkDate) {
		this.startWorkDate = startWorkDate;
	}

	public Date getOrgWorkDate() {
		return this.orgWorkDate;
	}

	public void setOrgWorkDate(Date orgWorkDate) {
		this.orgWorkDate = orgWorkDate;
	}

	public String getWorkCardId() {
		return this.workCardId;
	}

	public void setWorkCardId(String workCardId) {
		this.workCardId = workCardId;
	}

	public String getCardNum() {
		return this.cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}