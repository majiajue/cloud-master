/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, anycc.com
 * Filename:		net.hp.es.adm.healthcare.rphcp.entity.main.OrganizationRole.java
 * Class:			OrganizationRole
 * Date:			2013-4-15
 * Author:			<a href="mailto:anycc@gmail.com">anycc</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/

package com.datababys.entity.main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.datababys.entity.Idable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Range;


/**
 * 
 * @author <a href="mailto:anycc@gmail.com">anycc</a> Version 2.0.0
 * @since 2013-4-15 下午4:01:34
 */
@Entity
@Table(name = "sys_organization_role")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "net.hp.es.adm.healthcare.rphcp.entity.main.OrganizationRole")
public class OrganizationRole implements Idable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Range(min = 1L, max = 999L)
	@Column(length = 3, nullable = false)
	private Integer priority = Integer.valueOf(999);

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId")
	private Role role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizationId")
	private Organization organization;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}