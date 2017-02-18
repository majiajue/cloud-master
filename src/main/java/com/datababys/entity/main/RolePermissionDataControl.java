/**
 * 
 */
package com.datababys.entity.main;

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



/**
 * @author anycc
 *
 */
@Entity
@Table(name = "role_permission_data_control")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "net.hp.es.adm.healthcare.rphcp.entity.main.RolePermissionDataControl")
public class RolePermissionDataControl implements Idable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rolePermissionId")
	private RolePermission rolePermission;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dataControlId")
	private DataControl dataControl;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RolePermission getRolePermission() {
		return this.rolePermission;
	}

	public void setRolePermission(RolePermission rolePermission) {
		this.rolePermission = rolePermission;
	}

	public DataControl getDataControl() {
		return this.dataControl;
	}

	public void setDataControl(DataControl dataControl) {
		this.dataControl = dataControl;
	}
}
