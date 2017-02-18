/**
 * <pre>
 * Copyright:		Copyright(C) 2013-2015, anycc.com
 * Filename:		net.hp.es.adm.healthcare.rphcp.entity.main.Organization.java
 * Class:			Organization
 * Date:			2012-8-27
 * Author:			<a href="mailto:anycc@gmail.com">anycc</a>
 * Version          1.1.0
 * Description:
 *
 * </pre>
 **/

package com.datababys.entity.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.datababys.entity.Idable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;



/**
 * @author <a href="mailto:anycc@gmail.com">anycc</a> Version 1.1.0
 * @since 2012-8-27 下午3:25:15
 */
@Entity
@Table(name = "sys_organization")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "net.hp.es.adm.healthcare.rphcp.entity.main.Organization")
public class Organization implements Comparable<Organization>, Idable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 15)
	private String organcode;

	@Column(length = 70)
	private String organname;
	private String economycode;
	private String genrecode;
	private String managecode;
	private String gradecode;
	private String tel;
	private String fax;
	private String areaCode;
	private String mail;
	private String address;
	private String artificialPerson;
	private String artificialIdcard;
	private String artificialTel;
	private String artificialMobil;
	private String mNumber;
	private String fNumber;
	private Date startDate;
	private Date endDate;
	private String zonegb;
	private String gbcode;
	private String orgType;

	@NotNull
	@Range(min = 1L, max = 999L)
	@Column(length = 3, nullable = false)
	private Integer priority = Integer.valueOf(999);

	@Length(max = 256)
	@Column(length = 256)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	private Organization parent;

	@OneToMany(mappedBy = "parent", cascade = {
			javax.persistence.CascadeType.REFRESH,
			javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.REMOVE })
	@OrderBy("priority ASC")
	private List<Organization> children = new ArrayList<Organization>();

	@OneToMany(mappedBy = "organization", cascade = {
			javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.REMOVE })
	private List<User> users = new ArrayList<User>();

	@OneToMany(mappedBy = "organization", cascade = {
			javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.REMOVE })
	@OrderBy("priority ASC")
	private List<OrganizationRole> organizationRoles = new ArrayList<OrganizationRole>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "areaId")
	private Area area;
	private String isAccess;
	private String longitude;
	private String latitude;
	private String mtpCnt;
	private String residenciesNumber;
	private String opdoctorNumber;
	private String isReg;

	public Organization() {
	}

	public Organization(String organname) {
		this.organname = organname;
	}

	public Organization(Long id, String organname, String organcode) {
		this.parent = new Organization();
		this.parent.setId(id);
		this.organname = organname;
		this.organcode = organcode;
	}

	public Organization(Long id, String organname, String organcode,
			String longitude, String latitude, String orgType, String gbcode,
			String zonegb, String genrecode, String managecode, String address,
			String tel) {
		this.id = id;
		this.organname = organname;
		this.organcode = organcode;
		this.longitude = longitude;
		this.latitude = latitude;
		this.orgType = orgType;
		this.gbcode = gbcode;
		this.zonegb = zonegb;
		this.genrecode = genrecode;
		this.managecode = managecode;
		this.address = address;
		this.tel = tel;
	}

	public String getOrgType() {
		return this.orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getEconomycode() {
		return this.economycode;
	}

	public void setEconomycode(String economycode) {
		this.economycode = economycode;
	}

	public String getGenrecode() {
		return this.genrecode;
	}

	public void setGenrecode(String genrecode) {
		this.genrecode = genrecode;
	}

	public String getManagecode() {
		return this.managecode;
	}

	public void setManagecode(String managecode) {
		this.managecode = managecode;
	}

	public String getGradecode() {
		return this.gradecode;
	}

	public void setGradecode(String gradecode) {
		this.gradecode = gradecode;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArtificialPerson() {
		return this.artificialPerson;
	}

	public void setArtificialPerson(String artificialPerson) {
		this.artificialPerson = artificialPerson;
	}

	public String getArtificialIdcard() {
		return this.artificialIdcard;
	}

	public void setArtificialIdcard(String artificialIdcard) {
		this.artificialIdcard = artificialIdcard;
	}

	public String getArtificialTel() {
		return this.artificialTel;
	}

	public void setArtificialTel(String artificialTel) {
		this.artificialTel = artificialTel;
	}

	public String getArtificialMobil() {
		return this.artificialMobil;
	}

	public void setArtificialMobil(String artificialMobil) {
		this.artificialMobil = artificialMobil;
	}

	public String getmNumber() {
		return this.mNumber;
	}

	public void setmNumber(String mNumber) {
		this.mNumber = mNumber;
	}

	public String getfNumber() {
		return this.fNumber;
	}

	public void setfNumber(String fNumber) {
		this.fNumber = fNumber;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getZonegb() {
		return this.zonegb;
	}

	public void setZonegb(String zonegb) {
		this.zonegb = zonegb;
	}

	public String getGbcode() {
		return this.gbcode;
	}

	public void setGbcode(String gbcode) {
		this.gbcode = gbcode;
	}

	public String getResidenciesNumber() {
		return this.residenciesNumber;
	}

	public void setResidenciesNumber(String residenciesNumber) {
		this.residenciesNumber = residenciesNumber;
	}

	public String getOpdoctorNumber() {
		return this.opdoctorNumber;
	}

	public void setOpdoctorNumber(String opdoctorNumber) {
		this.opdoctorNumber = opdoctorNumber;
	}

	public String getIsReg() {
		return this.isReg;
	}

	public void setIsReg(String isReg) {
		this.isReg = isReg;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Organization getParent() {
		return this.parent;
	}

	public void setParent(Organization parent) {
		this.parent = parent;
	}

	public List<Organization> getChildren() {
		return this.children;
	}

	public void setChildren(List<Organization> children) {
		this.children = children;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<OrganizationRole> getOrganizationRoles() {
		return this.organizationRoles;
	}

	public void setOrganizationRoles(List<OrganizationRole> organizationRoles) {
		this.organizationRoles = organizationRoles;
	}

	public int compareTo(Organization org) {
		if (org == null)
			return -1;
		if (org == this)
			return 0;
		if (this.priority.intValue() < org.getPriority().intValue())
			return -1;
		if (this.priority.intValue() > org.getPriority().intValue()) {
			return 1;
		}

		return 0;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "Organization{" +
				"id=" + id +
				", organcode='" + organcode + '\'' +
				", organname='" + organname + '\'' +
				", economycode='" + economycode + '\'' +
				", genrecode='" + genrecode + '\'' +
				", managecode='" + managecode + '\'' +
				", gradecode='" + gradecode + '\'' +
				", tel='" + tel + '\'' +
				", fax='" + fax + '\'' +
				", areaCode='" + areaCode + '\'' +
				", mail='" + mail + '\'' +
				", address='" + address + '\'' +
				", artificialPerson='" + artificialPerson + '\'' +
				", artificialIdcard='" + artificialIdcard + '\'' +
				", artificialTel='" + artificialTel + '\'' +
				", artificialMobil='" + artificialMobil + '\'' +
				", mNumber='" + mNumber + '\'' +
				", fNumber='" + fNumber + '\'' +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", zonegb='" + zonegb + '\'' +
				", gbcode='" + gbcode + '\'' +
				", orgType='" + orgType + '\'' +
				", priority=" + priority +
				", description='" + description + '\'' +
				", parent=" + parent +
				", area=" + area +
				", isAccess='" + isAccess + '\'' +
				", longitude='" + longitude + '\'' +
				", latitude='" + latitude + '\'' +
				", mtpCnt='" + mtpCnt + '\'' +
				", residenciesNumber='" + residenciesNumber + '\'' +
				", opdoctorNumber='" + opdoctorNumber + '\'' +
				", isReg='" + isReg + '\'' +
				'}';
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgancode() {
		return this.organcode;
	}

	public void setOrgancode(String organcode) {
		this.organcode = organcode;
	}

	public String getOrganname() {
		return this.organname;
	}

	public void setOrganname(String organname) {
		this.organname = organname;
	}

	public String getIsAccess() {
		return this.isAccess;
	}

	public void setIsAccess(String isAccess) {
		this.isAccess = isAccess;
	}

	public String getMtpCnt() {
		return this.mtpCnt;
	}

	public void setMtpCnt(String mtpCnt) {
		this.mtpCnt = mtpCnt;
	}
}