/**
 * <pre>
 * Copyright:		Copyright(C) 2013-2015, anycc.com
 * Filename:		net.hp.es.adm.healthcare.rphcp.entity.authenticate.User.java
 * Class:			User
 * Date:			2012-8-2
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.datababys.entity.Idable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;



/**
 * 
 * @author <a href="mailto:anycc@gmail.com">anycc</a> Version 1.1.0
 * @since 2012-8-2 下午2:44:58
 */
@Entity
@Table(name = "sys_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "net.hp.es.adm.healthcare.rphcp.entity.main.User")
public class User implements Idable<Long> {
	public static final String STATUS_DISABLED = "disabled";
	public static final String STATUS_ENABLED = "enabled";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Length(max = 32)
	@Column(length = 32, nullable = false)
	private String realname;

	@NotBlank
	@Length(max = 32)
	@Column(length = 32, nullable = false, unique = true, updatable = true)
	private String username;

	@Column(length = 64, nullable = false)
	private String password;

	@Transient
	private String plainPassword;

	@Column(length = 32, nullable = false)
	private String salt;

	@Length(max = 32)
	@Column(length = 32)
	private String phone;

	@Email
	@Length(max = 128)
	@Column(length = 128)
	private String email;

	@NotBlank
	@Length(max = 16)
	@Column(length = 16, nullable = false)
	private String status = "enabled";

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date createTime;

	@OneToMany(mappedBy = "user", cascade = {
			javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.REMOVE }, orphanRemoval = true)
	@OrderBy("priority ASC")
	private List<UserRole> userRoles = new ArrayList<UserRole>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organizationId")
	private Organization organization;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "staffId")
	private Staff staff;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "depId")
	private Department department;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPlainPassword() {
		return this.plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
}