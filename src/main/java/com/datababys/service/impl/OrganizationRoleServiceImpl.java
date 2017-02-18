/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service.impl;


import com.datababys.dao.OrganizationRoleDAO;
import com.datababys.entity.main.OrganizationRole;
import com.datababys.service.OrganizationRoleService;
import com.datababys.util.Page;
import com.datababys.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrganizationRoleServiceImpl implements OrganizationRoleService {
	
	@Autowired
	private OrganizationRoleDAO organizationRoleDAO;

	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.OrganizationRoleService#get(java.lang.Long)  
	 */ 
	@Override
	public OrganizationRole get(Long id) {
		return organizationRoleDAO.findOne(id);
	}

	/*
	 * (non-Javadoc) 
	 * @see net.hp.es.adm.healthcare.rphcp.service.OrganizationRoleService#saveOrUpdate(net.hp.es.adm.healthcare.rphcp.entity.main.OrganizationRole)  
	 */
	@Override
	public void saveOrUpdate(OrganizationRole organizationRole) {
		organizationRoleDAO.save(organizationRole);
	}

	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.OrganizationRoleService#delete(java.lang.Long)  
	 */
	@Override
	public void delete(Long id) {
		organizationRoleDAO.delete(id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.OrganizationRoleService#findAll(net.hp.es.adm.healthcare.rphcp.util.dwz.Page)  
	 */
	@Override
	public List<OrganizationRole> findAll(Page page) {
		org.springframework.data.domain.Page<OrganizationRole> springDataPage = organizationRoleDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.OrganizationRoleService#findByExample(org.springframework.data.jpa.domain.Specification, net.hp.es.adm.healthcare.rphcp.util.dwz.Page)	
	 */
	@Override
	public List<OrganizationRole> findByExample(
			Specification<OrganizationRole> specification, Page page) {
		org.springframework.data.domain.Page<OrganizationRole> springDataPage = organizationRoleDAO.findAll(specification, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/* (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.OrganizationRoleService#findByOrganizationId(java.lang.Long)
	 */
	@Override
	public List<OrganizationRole> findByOrganizationId(Long organizationId) {
		return organizationRoleDAO.findByOrganizationId(organizationId);
	}
}
