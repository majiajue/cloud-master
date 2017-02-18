/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service;


import com.datababys.entity.main.Permission;
import com.datababys.util.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface PermissionService {
	Permission get(Long id);

	void saveOrUpdate(Permission permission);

	void delete(Long id);
	
	List<Permission> findAll(Page page);
	
	List<Permission> findByExample(Specification<Permission> specification, Page page);
}
