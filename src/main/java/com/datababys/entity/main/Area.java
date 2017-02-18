/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.entity.main;


import com.datababys.entity.Idable;

import com.fasterxml.jackson.annotation.JsonIgnore;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
@Entity
@Table(name="sys_area")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="com.datababys.entity.main.Area")
public class Area implements Comparable<Area>, Idable<Integer>,Serializable {
    private static final long serialVersionUID = -8941416721801223096L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
	/**
	 * 12位行政区划代码
	 */
    @Column(nullable=false, length=12)
    private String gbcode;
    
	/**
	 * 10位行政区划代码
	 */
    @Column(length=10)
    private String zonegb;
    
	/**
	 * 地区名称
	 */
    @Column(nullable=false, length=30)
    private String name;
    
	/**
	 * 详细地区名称
	 */
    @Column(length=100)
    private String lname;
    
	/**
	 * 辖区行政级别代码
	 */
    @Column(length=1)
    private String dlevel;
    
    private String population ;
    
    /*private String permanentpop;*/
    
    private String farmingpop;
    
    private String articipatingpop;
    
    private Double  lat;
    
    private Double  lng;
    
    
	 

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	 

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}



	/**
	 * 越小优先级越高
	 */
	@NotNull
	@Range(min=1, max=999)
	@Column(length=3, nullable=false)
    //private Integer priority = 999;
	 private Integer priority = Integer.valueOf(999);
	/**
	 * 上级区域
	 */
	@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="parentId")
	private Area parent;

	//@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
	@OneToMany(mappedBy="parent", fetch=FetchType.LAZY, cascade={CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE})
	@OrderBy("priority ASC")
	private List<Area> children = new ArrayList<Area>();
	
	@OneToMany(mappedBy="area", cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
	private List<Organization> organizations = new ArrayList<Organization>();
	
	/**
	 * 年份
	 */
    @Column(length=4)
    private String year;
    
    /**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @param gbcode the gbcode to set
	 */
    public void setGbcode(String gbcode){
       this.gbcode = gbcode;
    }
    
    /**
     * @return the gbcode 
     */
    public String getGbcode(){
       return this.gbcode;
    }
	
	/**
	 * @param zonegb the zonegb to set
	 */
    public void setZonegb(String zonegb){
       this.zonegb = zonegb;
    }
    
    /**
     * @return the zonegb 
     */
    public String getZonegb(){
       return this.zonegb;
    }
	
	/**
	 * @param name the name to set
	 */
    public void setName(String name){
       this.name = name;
    }
    
    /**
     * @return the name 
     */
    public String getName(){
       return this.name;
    }
	
	/**
	 * @param lname the lname to set
	 */
    public void setLname(String lname){
       this.lname = lname;
    }
    
    /**
     * @return the lname 
     */
    public String getLname(){
       return this.lname;
    }
	
	/**
	 * @param dlevel the dlevel to set
	 */
    public void setDlevel(String dlevel){
       this.dlevel = dlevel;
    }
    
    /**
     * @return the dlevel 
     */
    public String getDlevel(){
       return this.dlevel;
    }
	
    /**  
	 * 返回 children 的值   
	 * @return children  
	 */
	@JsonIgnore
	public List<Area> getChildren() {
		return children;
	}

	/**  
	 * 设置 children 的值  
	 * @param children
	 */
	public void setChildren(List<Area> children) {
		this.children = children;
	}
    
	/**
	 * @param priority the priority to set
	 */
    public void setPriority(Integer priority){
       this.priority = priority;
    }
    
    /**
     * @return the priority 
     */
    public Integer getPriority(){
       return this.priority;
    }
	
    /**  
	 * 返回 parent 的值   
	 * @return parent  
	 */
	@JsonIgnore
	public Area getParent() {
		return parent;
	}

	/**  
	 * 设置 parent 的值  
	 * @param parent
	 */
	public void setParent(Area parent) {
		this.parent = parent;
	}

	
	/**
	 * @param year the year to set
	 */
    public void setYear(String year){
       this.year = year;
    }
    
    /**
     * @return the year 
     */
    public String getYear(){
       return this.year;
    }
    /**  
	 * 返回 users 的值   
	 * @return users  
	 */
	@JsonIgnore
	public List<Organization> getOrganizations() {
		return organizations;
	}

	/**  
	 * 设置 users 的值  
	 * @param organizations
	 */
	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	@Override
	public String toString() {
		return "Area{" +
				"id=" + id +
				", gbcode='" + gbcode + '\'' +
				", zonegb='" + zonegb + '\'' +
				", name='" + name + '\'' +
				", lname='" + lname + '\'' +
				", dlevel='" + dlevel + '\'' +
				", population='" + population + '\'' +
				", farmingpop='" + farmingpop + '\'' +
				", articipatingpop='" + articipatingpop + '\'' +
				", lat=" + lat +
				", lng=" + lng +
				", priority=" + priority +
				", year='" + year + '\'' +
				'}';
	}

	/*
             *
             */
	@Override
	public int compareTo(Area area) {
		if (area == null) {
			return -1;
		} else if (area == this) {
			return 0;
		} else if (this.priority < area.getPriority()) {
			return -1;
		} else if (this.priority > area.getPriority()) {
			return 1;
		}

		return 0;	
	}



	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getFarmingpop() {
		return farmingpop;
	}

	public void setFarmingpop(String farmingpop) {
		this.farmingpop = farmingpop;
	}

	public String getArticipatingpop() {
		return articipatingpop;
	}

	public void setArticipatingpop(String articipatingpop) {
		this.articipatingpop = articipatingpop;
	}

	/*public String getPermanentpop() {
		return permanentpop;
	}

	public void setPermanentpop(String permanentpop) {
		this.permanentpop = permanentpop;
	}*/
}
