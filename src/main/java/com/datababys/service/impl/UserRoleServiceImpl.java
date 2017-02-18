/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service.impl;


import com.datababys.dao.UserRoleDAO;
import com.datababys.entity.main.UserRole;
import com.datababys.service.UserRoleService;
import com.datababys.util.Page;
import com.datababys.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {
	
	@Autowired
	private UserRoleDAO userRoleDAO;

	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.UserRoleService#get(java.lang.Long)  
	 */ 
	@Override
	public UserRole get(Long id) {
		return userRoleDAO.findOne(id);
	}

	/*
	 * (non-Javadoc) 
	 * @see net.hp.es.adm.healthcare.rphcp.service.UserRoleService#saveOrUpdate(net.hp.es.adm.healthcare.rphcp.entity.main.UserRole)  
	 */
	@Override
	public void saveOrUpdate(UserRole userRole) {
		userRoleDAO.save(userRole);
	}

	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.UserRoleService#delete(java.lang.Long)  
	 */
	@Override
	public void delete(Long id) {
		userRoleDAO.delete(id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.UserRoleService#findAll(net.hp.es.adm.healthcare.rphcp.util.dwz.Page)  
	 */
	@Override
	public List<UserRole> findAll(Page page) {
		org.springframework.data.domain.Page<UserRole> springDataPage = userRoleDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.UserRoleService#findByExample(org.springframework.data.jpa.domain.Specification, net.hp.es.adm.healthcare.rphcp.util.dwz.Page)	
	 */
	@Override
	public List<UserRole> findByExample(
			Specification<UserRole> specification, Page page) {
		org.springframework.data.domain.Page<UserRole> springDataPage = userRoleDAO.findAll(specification, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/* (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.UserRoleService#findByUserId(java.lang.Long)
	 */
	@Override
	public List<UserRole> findByUserId(Long userId) {
		return userRoleDAO.findByUserId(userId);
	}
	
}
