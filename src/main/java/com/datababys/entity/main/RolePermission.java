/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, anycc.com
 * Filename:		net.hp.es.adm.healthcare.rphcp.entity.main.RolePermission.java
 * Class:			RolePermission
 * Date:			2013-4-16
 * Author:			<a href="mailto:anycc@gmail.com">anycc</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/

package com.datababys.entity.main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.datababys.entity.Idable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



/**
 * 
 * @author <a href="mailto:anycc@gmail.com">anycc</a> Version 2.0.0
 * @since 2013-4-16 下午1:47:51
 */
@Entity
@Table(name = "sys_role_permission")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "net.hp.es.adm.healthcare.rphcp.entity.main.RolePermission")

public class RolePermission
implements Idable<Long>
{

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;

@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="roleId")
private Role role;

@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="permissionId")
private Permission permission;

@OneToMany(mappedBy="rolePermission", cascade={javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.REMOVE}, orphanRemoval=true)
private List<RolePermissionDataControl> rolePermissionDataControls = new ArrayList<RolePermissionDataControl> ();

public Long getId()
{
  return this.id;
}

public void setId(Long id) {
  this.id = id;
}

public Role getRole()
{
  return this.role;
}

public void setRole(Role role)
{
  this.role = role;
}

public Permission getPermission()
{
  return this.permission;
}

public void setPermission(Permission permission)
{
  this.permission = permission;
}

public List<RolePermissionDataControl> getRolePermissionDataControls()
{
  return this.rolePermissionDataControls;
}

public void setRolePermissionDataControls(List<RolePermissionDataControl> rolePermissionDataControls)
{
  this.rolePermissionDataControls = rolePermissionDataControls;
}

public boolean equals(Object obj)
{
  return EqualsBuilder.reflectionEquals(this, obj, false);
}

public int hashCode()
{
  return HashCodeBuilder.reflectionHashCode(this, false);
}
}
