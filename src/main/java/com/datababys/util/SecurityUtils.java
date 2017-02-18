/**
 * 
 */
package com.datababys.util;

import com.datababys.entity.main.User;
import com.datababys.shiro.ShiroUser;
import org.apache.shiro.subject.Subject;



/**
 * @author anycc
 *
 */
public abstract class SecurityUtils {
	public static User getLoginUser() {
		return getShiroUser().getUser();
	}
	
	public static ShiroUser getShiroUser() {
		Subject subject = getSubject();
		ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
		
		return shiroUser;
	}

	public static Subject getSubject() {
		return org.apache.shiro.SecurityUtils.getSubject();
	}
}
