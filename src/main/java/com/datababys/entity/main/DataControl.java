/**
 * 
 */
package com.datababys.entity.main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.datababys.entity.Idable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


/**
 * @author anycc
 *
 */
@Entity
@Table(name = "sys_data_control")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "com.datababys.entity.main.DataControl")
public class DataControl implements Idable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Length(max = 64)
	@Column(length = 64, nullable = false, unique = true, updatable = false)
	private String name;

	@Length(max = 256)
	@Column(length = 256)
	private String description;

	@Length(max = 10240)
	@Column(length = 10240)
	private String control;

	@OneToMany(mappedBy = "dataControl", cascade = {
			javax.persistence.CascadeType.PERSIST,
			javax.persistence.CascadeType.REMOVE }, orphanRemoval = true)
	private List<RolePermissionDataControl> rolePermissionDataControls = new ArrayList<RolePermissionDataControl>();

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

	public String getControl() {
		return this.control;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public List<RolePermissionDataControl> getRolePermissionDataControls() {
		return this.rolePermissionDataControls;
	}

	public void setRolePermissionDataControls(
			List<RolePermissionDataControl> rolePermissionDataControls) {
		this.rolePermissionDataControls = rolePermissionDataControls;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
