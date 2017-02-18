/**
 * <pre>
 * Copyright:		Copyright(C) 2013-2015, anycc.com
 * Filename:		net.hp.es.adm.healthcare.rphcp.entity.main.Module.java
 * Class:			Module
 * Date:			2012-8-2
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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.datababys.entity.Idable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/** 
 * 	
 * @author 	<a href="mailto:anycc@gmail.com">anycc</a>
 * Version  1.1.0
 * @since   2012-8-2 下午5:36:39 
 */
@Entity
@Table(name="sys_module")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="net.hp.es.adm.healthcare.rphcp.entity.main.Module")
@SequenceGenerator(name="moduleSEQ",sequenceName="moduleSEQ_DB")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class Module
implements Comparable<Module>, Idable<Long>
{

@Id
@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="moduleSEQ")
private Long id;

@NotBlank
@Length(max=64)
@Column(length=64, nullable=false)
private String name;

@Column(length=256)
private String className;

@NotBlank
@Length(max=256)
@Column(length=256, nullable=false)
private String url;

@Column(length=32)
private String icon;

@Length(max=256)
@Column(length=256)
private String description;

@NotBlank
@Length(max=32)
@Column(length=32, nullable=false, unique=true)
private String sn;

@NotNull
@Range(min=1L, max=999L)
@Column(length=3, nullable=false)
private Integer priority = Integer.valueOf(999);

@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="parentId")
private Module parent;

@OneToMany(mappedBy="parent", cascade={javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.REMOVE})
@OrderBy("priority ASC")
private List<Module> children = new ArrayList<Module>();

@OneToMany(mappedBy="module", cascade={javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.REMOVE}, orphanRemoval=true)
private List<Permission> permissions = new ArrayList<Permission>();

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

public String getClassName()
{
  return this.className;
}

public void setClassName(String className)
{
  this.className = className;
}

public String getUrl()
{
  return this.url;
}

public void setUrl(String url)
{
  this.url = url;
}

public String getDescription()
{
  return this.description;
}

public void setDescription(String description)
{
  this.description = description;
}

public Integer getPriority()
{
  return this.priority;
}

public void setPriority(Integer priority)
{
  this.priority = priority;
}

public Module getParent()
{
  return this.parent;
}

public void setParent(Module parent)
{
  this.parent = parent;
}

public List<Module> getChildren()
{
  return this.children;
}

public void setChildren(List<Module> children)
{
  this.children = children;
}

public String getSn()
{
  return this.sn;
}

public void setSn(String sn)
{
  this.sn = sn;
}

public List<Permission> getPermissions()
{
  return this.permissions;
}

public void setPermissions(List<Permission> permissions)
{
  this.permissions = permissions;
}

public String getIcon()
{
  return this.icon;
}

public void setIcon(String icon)
{
  this.icon = icon;
}

public int compareTo(Module m)
{
  if (m == null)
    return -1;
  if (m == this)
    return 0;
  if (this.priority.intValue() < m.getPriority().intValue())
    return -1;
  if (this.priority.intValue() > m.getPriority().intValue()) {
    return 1;
  }

  return 0;
}

public String toString()
{
  return ToStringBuilder.reflectionToString(this);
}
}