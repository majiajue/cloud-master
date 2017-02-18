/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service.impl;


import com.datababys.dao.RolePermissionDataControlDAO;
import com.datababys.entity.main.RolePermissionDataControl;
import com.datababys.service.RolePermissionDataControlService;
import com.datababys.util.Page;
import com.datababys.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RolePermissionDataControlServiceImpl implements RolePermissionDataControlService {
	
	@Autowired
	private RolePermissionDataControlDAO rolePermissionDataControlDAO;

	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.RolePermissionDataControlService#get(java.lang.Long)  
	 */ 
	@Override
	public RolePermissionDataControl get(Long id) {
		return rolePermissionDataControlDAO.findOne(id);
	}

	/*
	 * (non-Javadoc) 
	 * @see net.hp.es.adm.healthcare.rphcp.service.RolePermissionDataControlService#saveOrUpdate(net.hp.es.adm.healthcare.rphcp.entity.main.RolePermissionDataControl)  
	 */
	@Override
	public void saveOrUpdate(RolePermissionDataControl rolePermissionDataControl) {
		rolePermissionDataControlDAO.save(rolePermissionDataControl);
	}

	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.RolePermissionDataControlService#delete(java.lang.Long)  
	 */
	@Override
	public void delete(Long id) {
		rolePermissionDataControlDAO.delete(id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.RolePermissionDataControlService#findAll(net.hp.es.adm.healthcare.rphcp.util.dwz.Page)  
	 */
	@Override
	public List<RolePermissionDataControl> findAll(Page page) {
		org.springframework.data.domain.Page<RolePermissionDataControl> springDataPage = rolePermissionDataControlDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.RolePermissionDataControlService#findByExample(org.springframework.data.jpa.domain.Specification, net.hp.es.adm.healthcare.rphcp.util.dwz.Page)	
	 */
	@Override
	public List<RolePermissionDataControl> findByExample(
			Specification<RolePermissionDataControl> specification, Page page) {
		org.springframework.data.domain.Page<RolePermissionDataControl> springDataPage = rolePermissionDataControlDAO.findAll(specification, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/* (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.RolePermissionDataControlService#save(java.util.List)
	 */
	@Override
	public void save(List<RolePermissionDataControl> newRList) {
		rolePermissionDataControlDAO.save(newRList);
	}

	/* (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.RolePermissionDataControlService#delete(java.util.List)
	 */
	@Override
	public void delete(List<RolePermissionDataControl> delRList) {
		rolePermissionDataControlDAO.delete(delRList);
	}

	/* (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.RolePermissionDataControlService#findByRolePermissionRoleId(java.lang.Long)
	 */
	@Override
	public List<RolePermissionDataControl> findByRolePermissionRoleId(Long id) {
		return rolePermissionDataControlDAO.findByRolePermissionRoleId(id);
	}
	
}
