/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service.impl;


import com.datababys.dao.PermissionDAO;
import com.datababys.entity.main.Permission;
import com.datababys.service.PermissionService;
import com.datababys.util.Page;
import com.datababys.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionDAO permissionDAO;

	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.PermissionService#get(java.lang.Long)  
	 */ 
	@Override
	public Permission get(Long id) {
		return permissionDAO.findOne(id);
	}

	/*
	 * (non-Javadoc) 
	 * @see net.hp.es.adm.healthcare.rphcp.service.PermissionService#saveOrUpdate(net.hp.es.adm.healthcare.rphcp.entity.main.Permission)  
	 */
	@Override
	public void saveOrUpdate(Permission permission) {
		permissionDAO.save(permission);
	}

	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.PermissionService#delete(java.lang.Long)  
	 */
	@Override
	public void delete(Long id) {
		permissionDAO.delete(id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.PermissionService#findAll(net.hp.es.adm.healthcare.rphcp.util.dwz.Page)  
	 */
	@Override
	public List<Permission> findAll(Page page) {
		org.springframework.data.domain.Page<Permission> springDataPage = permissionDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.PermissionService#findByExample(org.springframework.data.jpa.domain.Specification, net.hp.es.adm.healthcare.rphcp.util.dwz.Page)	
	 */
	@Override
	public List<Permission> findByExample(
			Specification<Permission> specification, Page page) {
		org.springframework.data.domain.Page<Permission> springDataPage = permissionDAO.findAll(specification, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
}
