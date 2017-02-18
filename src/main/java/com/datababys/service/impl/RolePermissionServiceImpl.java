/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service.impl;


import com.datababys.dao.RolePermissionDAO;
import com.datababys.entity.main.RolePermission;
import com.datababys.service.RolePermissionService;
import com.datababys.util.Page;
import com.datababys.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {
	
	@Autowired
	private RolePermissionDAO rolePermissionDAO;

	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.RolePermissionService#get(java.lang.Long)  
	 */ 
	@Override
	public RolePermission get(Long id) {
		return rolePermissionDAO.findOne(id);
	}

	/*
	 * (non-Javadoc) 
	 * @see net.hp.es.adm.healthcare.rphcp.service.RolePermissionService#saveOrUpdate(net.hp.es.adm.healthcare.rphcp.entity.main.RolePermission)  
	 */
	@Override
	public void saveOrUpdate(RolePermission rolePermission) {
		rolePermissionDAO.save(rolePermission);
	}

	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.RolePermissionService#delete(java.lang.Long)  
	 */
	@Override
	public void delete(Long id) {
		rolePermissionDAO.delete(id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.RolePermissionService#findAll(net.hp.es.adm.healthcare.rphcp.util.dwz.Page)  
	 */
	@Override
	public List<RolePermission> findAll(Page page) {
		org.springframework.data.domain.Page<RolePermission> springDataPage = rolePermissionDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.RolePermissionService#findByExample(org.springframework.data.jpa.domain.Specification, net.hp.es.adm.healthcare.rphcp.util.dwz.Page)	
	 */
	@Override
	public List<RolePermission> findByExample(
			Specification<RolePermission> specification, Page page) {
		org.springframework.data.domain.Page<RolePermission> springDataPage = rolePermissionDAO.findAll(specification, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/* (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.RolePermissionService#findByRoleId(java.lang.Long)
	 */
	@Override
	public List<RolePermission> findByRoleId(Long id) {
		return rolePermissionDAO.findByRoleId(id);
	}

	/* (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.RolePermissionService#save(java.util.List)
	 */
	@Override
	public void save(List<RolePermission> newRList) {
		rolePermissionDAO.save(newRList);
	}

	/* (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.RolePermissionService#delete(java.util.List)
	 */
	@Override
	public void delete(List<RolePermission> delRList) {
		rolePermissionDAO.delete(delRList);
	}
}
