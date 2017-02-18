/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service.impl;


import com.datababys.dao.UserDAO;
import com.datababys.entity.main.User;
import com.datababys.exception.ExistedException;
import com.datababys.exception.IncorrectPasswordException;
import com.datababys.exception.NotDeletedException;
import com.datababys.service.UserService;
import com.datababys.shiro.ShiroDbRealm;
import com.datababys.util.Page;
import com.datababys.util.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ShiroDbRealm shiroRealm;

	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.UserService#get(java.lang.Long)  
	 */ 
	@Override
	public User get(Long id) {
		return userDAO.findOne(id);
	}

	/*
	 * (non-Javadoc) 
	 * @see net.hp.es.adm.healthcare.rphcp.service.UserService#saveOrUpdate(net.hp.es.adm.healthcare.rphcp.entity.main.User)  
	 */
	@Override
	public void saveOrUpdate(User user) {
		if (user.getId() == null) {
			if (userDAO.getByUsername(user.getUsername()) != null) {
				throw new ExistedException("登录名：" + user.getUsername() + "已存在。");
			}
			
			//设定安全的密码，使用passwordService提供的salt并经过1024次 sha-1 hash
			if (StringUtils.isNotBlank(user.getPlainPassword()) && shiroRealm != null) {
				ShiroDbRealm.HashPassword hashPassword = ShiroDbRealm.encryptPassword(user.getPlainPassword());
				user.setSalt(hashPassword.salt);
				user.setPassword(hashPassword.password);
			}
		}
		
		userDAO.save(user);
		shiroRealm.clearCachedAuthorizationInfo(user.getUsername());
	}

	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.UserService#delete(java.lang.Long)  
	 */
	@Override
	public void delete(Long id) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}，尝试删除超级管理员用户", SecurityUtils.getSubject()
					.getPrincipal() + "。");
			throw new NotDeletedException("不能删除超级管理员用户。");
		}
		User user = userDAO.findOne(id);
		userDAO.delete(user.getId());
		
		// TODO 从shiro中注销
		shiroRealm.clearCachedAuthorizationInfo(user.getUsername());
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.UserService#findAll(net.hp.es.adm.healthcare.rphcp.util.dwz.Page)  
	 */
	@Override
	public List<User> findAll(Page page) {
		org.springframework.data.domain.Page<User> springDataPage = userDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.UserService#findByExample(org.springframework.data.jpa.domain.Specification, net.hp.es.adm.healthcare.rphcp.util.dwz.Page)	
	 */
	@Override
	public List<User> findByExample(
			Specification<User> specification, Page page) {
		org.springframework.data.domain.Page<User> springDataPage = userDAO.findAll(specification, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}

	/* (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.UserService#updatePwd(net.hp.es.adm.healthcare.rphcp.entity.main.User, java.lang.String)
	 */
	@Override
	public void updatePwd(User user, String newPwd) throws ServiceException {
		//设定安全的密码，使用passwordService提供的salt并经过1024次 sha-1 hash
		boolean isMatch = ShiroDbRealm.validatePassword(user.getPlainPassword(), user.getPassword(), user.getSalt());
		if (isMatch) {
			ShiroDbRealm.HashPassword hashPassword = ShiroDbRealm.encryptPassword(newPwd);
			user.setSalt(hashPassword.salt);
			user.setPassword(hashPassword.password);
			
			userDAO.save(user);
			shiroRealm.clearCachedAuthorizationInfo(user.getUsername());
			
			return; 
		}
		
		throw new IncorrectPasswordException("用户密码错误！");
	}

	/* (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.UserService#resetPwd(net.hp.es.adm.healthcare.rphcp.entity.main.User, java.lang.String)
	 */
	@Override
	public void resetPwd(User user, String newPwd) {
		if (newPwd == null) {
			newPwd = "123456";
		}
		
		ShiroDbRealm.HashPassword hashPassword = ShiroDbRealm.encryptPassword(newPwd);
		user.setSalt(hashPassword.salt);
		user.setPassword(hashPassword.password);
		
		userDAO.save(user);
	}

	/* (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.UserService#getByUsername(java.lang.String)
	 */
	@Override
	public User getByUsername(String username) {
		User user=userDAO.getByUsername(username);
		if(user==null){
			user=userDAO.getByPhone(username);
		}
		if(user==null){
			user=userDAO.getByEmail(username);
		}
		return user;
	}
	
	/**
	 * 查询全部用户列表
	 * @return
	 */
	public List<User> findList(){
		return userDAO.findAll();
	}
}
