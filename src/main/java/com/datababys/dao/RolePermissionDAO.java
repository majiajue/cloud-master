/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.dao;


import com.datababys.entity.main.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RolePermissionDAO extends JpaRepository<RolePermission, Long>, JpaSpecificationExecutor<RolePermission> {

	/**
	 *
	 * @return
	 */
	List<RolePermission> findByRoleId(Long roleId);

}