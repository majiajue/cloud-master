/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.dao;


import com.datababys.entity.main.RolePermissionDataControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RolePermissionDataControlDAO extends JpaRepository<RolePermissionDataControl, Long>, JpaSpecificationExecutor<RolePermissionDataControl> {
	List<RolePermissionDataControl> findByRolePermissionRoleId(Long id);
}