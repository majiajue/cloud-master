/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service;

;import com.datababys.entity.main.Role;
import com.datababys.util.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface RoleService {
	Role get(Long id);

	void saveOrUpdate(Role role);

	void delete(Long id);
	
	List<Role> findAll(Page page);
	
	List<Role> findByExample(Specification<Role> specification, Page page);
}
