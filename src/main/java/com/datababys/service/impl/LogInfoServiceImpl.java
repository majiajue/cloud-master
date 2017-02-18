/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service.impl;


import com.datababys.dao.LogInfoDAO;
import com.datababys.entity.main.LogInfo;
import com.datababys.service.LogInfoService;
import com.datababys.util.Page;
import com.datababys.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LogInfoServiceImpl implements LogInfoService {
	
	@Autowired
	private LogInfoDAO logInfoDAO;

	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.LogInfoService#get(java.lang.Long)  
	 */ 
	@Override
	public LogInfo get(Long id) {
		return logInfoDAO.findOne(id);
	}

	/*
	 * (non-Javadoc) 
	 * @see net.hp.es.adm.healthcare.rphcp.service.LogInfoService#saveOrUpdate(net.hp.es.adm.healthcare.rphcp.entity.main.LogInfo)  
	 */
	@Override
	public void saveOrUpdate(LogInfo logInfo) {
		logInfoDAO.save(logInfo);
	}

	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.LogInfoService#delete(java.lang.Long)  
	 */
	@Override
	public void delete(Long id) {
		logInfoDAO.delete(id);
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.LogInfoService#findAll(net.hp.es.adm.healthcare.rphcp.util.dwz.Page)  
	 */
	@Override
	public List<LogInfo> findAll(Page page) {
		org.springframework.data.domain.Page<LogInfo> springDataPage = logInfoDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.LogInfoService#findByExample(org.springframework.data.jpa.domain.Specification, net.hp.es.adm.healthcare.rphcp.util.dwz.Page)	
	 */
	@Override
	public List<LogInfo> findByExample(
			Specification<LogInfo> specification, Page page) {
		org.springframework.data.domain.Page<LogInfo> springDataPage = logInfoDAO.findAll(specification, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
}
