/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service;

import com.datababys.entity.main.OrganizationRole;
import com.datababys.util.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface OrganizationRoleService {
	OrganizationRole get(Long id);

	void saveOrUpdate(OrganizationRole organizationRole);

	void delete(Long id);
	
	List<OrganizationRole> findAll(Page page);
	
	List<OrganizationRole> findByExample(Specification<OrganizationRole> specification, Page page);
	
	/**
	 * 根据organizationId，找到已分配的角色。
	 * 描述
	 * @param organizationId
	 * @return
	 */
	List<OrganizationRole> findByOrganizationId(Long organizationId);
}
