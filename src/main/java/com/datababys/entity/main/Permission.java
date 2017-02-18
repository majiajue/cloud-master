/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, anycc.com
 * Filename:		net.hp.es.adm.healthcare.rphcp.entity.main.CustomPermission.java
 * Class:			CustomPermission
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

import javax.persistence.Column;
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
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;



/** 
 * 除了CRUD以外的自定义权限 	
 * @author 	<a href="mailto:anycc@gmail.com">anycc</a>
 * Version  2.0.0
 * @since   2013-4-16 下午1:34:54 
 */
@Entity
@Table(name="sys_permission")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="net.hp.es.adm.healthcare.rphcp.entity.main.Permission")
public class Permission
implements Idable<Long>
{
public static final String PERMISSION_SHOW = "show";
public static final String PERMISSION_CREATE = "save";
public static final String PERMISSION_READ = "view";
public static final String PERMISSION_UPDATE = "edit";
public static final String PERMISSION_DELETE = "delete";

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;

@NotBlank
@Length(max=64)
@Column(length=64, nullable=false)
private String name;

@NotBlank
@Length(max=16)
@Column(length=16, nullable=false)
private String sn;

@Length(max=256)
@Column(length=256)
private String description;

@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="moduleId")
private Module module;

@OneToMany(mappedBy="permission", cascade={javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.REMOVE}, orphanRemoval=true)
private List<RolePermission> rolePermissiones = new ArrayList<RolePermission>();

public Long getId()
{
  return this.id;
}

public void setId(Long id) {
  this.id = id;
}

public String getName()
{
  return this.name;
}

public void setName(String name)
{
  this.name = name;
}

public String getDescription()
{
  return this.description;
}

public void setDescription(String description)
{
  this.description = description;
}

public Module getModule()
{
  return this.module;
}

public void setModule(Module module)
{
  this.module = module;
}

public List<RolePermission> getRolePermissiones()
{
  return this.rolePermissiones;
}

public void setRolePermissiones(List<RolePermission> rolePermissiones)
{
  this.rolePermissiones = rolePermissiones;
}

public String getSn()
{
  return this.sn;
}

public void setSn(String sn)
{
  this.sn = sn;
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
