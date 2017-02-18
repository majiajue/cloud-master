/**
 * <pre>
 * Copyright:		Copyright(C) 2013-2015, anycc.com
 * Filename:		net.hp.es.adm.healthcare.rphcp.entity.Role.java
 * Class:			Role
 * Date:			2012-6-7
 * Author:			<a href="mailto:anycc@gmail.com">anycc</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package com.datababys.entity.main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.datababys.entity.Idable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;



/**
 * 
 * @author <a href="mailto:anycc@gmail.com">anycc</a> Version 1.1.0
 * @since 2012-6-7 上午11:07:45
 */
@Entity
@Table(name = "sys_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "net.hp.es.adm.healthcare.rphcp.entity.main.Role")
@SequenceGenerator(name = "roleSEQ", sequenceName = "roleSEQ_DB")
public class Role implements Idable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSEQ")
	private Long id;

	@NotBlank
	@Length(max = 64)
	@Column(length = 64, nullable = false, unique = true)
	private String name;

	@Length(max = 256)
	@Column(length = 256)
	private String description;

	@OneToMany(mappedBy = "role", cascade = {
			javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.REMOVE }, orphanRemoval = true)
	@OrderBy("priority ASC")
	private List<UserRole> userRoles = new ArrayList<UserRole>();

	@OneToMany(mappedBy = "role", cascade = {
			javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.REMOVE }, orphanRemoval = true)
	@OrderBy("priority ASC")
	private List<OrganizationRole> organizationRoles = new ArrayList<OrganizationRole>();

	@OneToMany(mappedBy = "role", cascade = {
			javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.REMOVE }, orphanRemoval = true)
	private List<RolePermission> rolePermissions = new ArrayList<RolePermission>();

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public List<OrganizationRole> getOrganizationRoles() {
		return this.organizationRoles;
	}

	public void setOrganizationRoles(List<OrganizationRole> organizationRoles) {
		this.organizationRoles = organizationRoles;
	}

	public List<RolePermission> getRolePermissions() {
		return this.rolePermissions;
	}

	public void setRolePermissions(List<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}
}
