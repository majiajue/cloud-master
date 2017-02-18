/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service;


import com.datababys.entity.main.RolePermission;
import com.datababys.util.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface RolePermissionService {
	RolePermission get(Long id);

	void saveOrUpdate(RolePermission rolePermission);

	void delete(Long id);
	
	List<RolePermission> findAll(Page page);
	
	List<RolePermission> findByExample(Specification<RolePermission> specification, Page page);

	/**
	 * @param id
	 * @return
	 */
	List<RolePermission> findByRoleId(Long id);

	/**
	 * @param newRList
	 */
	void save(List<RolePermission> newRList);
	
	void delete(List<RolePermission> delRList);
}
