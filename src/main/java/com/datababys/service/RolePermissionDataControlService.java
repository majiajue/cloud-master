/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service;


import com.datababys.entity.main.RolePermissionDataControl;
import com.datababys.util.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface RolePermissionDataControlService {
	RolePermissionDataControl get(Long id);

	void saveOrUpdate(RolePermissionDataControl rolePermissionDataControl);

	void delete(Long id);
	
	List<RolePermissionDataControl> findAll(Page page);
	
	List<RolePermissionDataControl> findByExample(Specification<RolePermissionDataControl> specification, Page page);

	/**
	 * @param newRList
	 */
	void save(List<RolePermissionDataControl> newRList);

	/**
	 * @param delRList
	 */
	void delete(List<RolePermissionDataControl> delRList);

	/**
	 * @param id
	 * @return
	 */
	List<RolePermissionDataControl> findByRolePermissionRoleId(Long id);
}
