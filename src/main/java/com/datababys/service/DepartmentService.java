/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service;


import com.datababys.entity.main.Department;
import com.datababys.entity.main.Organization;
import com.datababys.util.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface DepartmentService {
	Department get(Long id);

	void saveOrUpdate(Department department);

	void delete(Long id);
	
	List<Department> findList();
	
	List<Department> findAll(Page page);
	
	List<Department> findByExample(Specification<Department> specification, Page page);

	Department getByDepartmentname(String depname);
	
	Department getTree();
	
	Department getTree(Long id);
	
	List<Organization> organizationQuery(Long id);
	
	List<Department> getDeptByOrgId(Long id);
}
