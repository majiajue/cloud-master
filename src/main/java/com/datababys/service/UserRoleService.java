/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service;


import com.datababys.entity.main.UserRole;
import com.datababys.util.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface UserRoleService {
	UserRole get(Long id);

	void saveOrUpdate(UserRole userRole);

	void delete(Long id);
	
	List<UserRole> findAll(Page page);
	
	List<UserRole> findByExample(Specification<UserRole> specification, Page page);
	
	List<UserRole> findByUserId(Long userId);
}
