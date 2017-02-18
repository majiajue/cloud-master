/**
 * <pre>
 * Copyright:		Copyright(C) 2013-2015, anycc.com
 * Filename:		net.hp.es.adm.healthcare.rphcp.shiro.BaseFormAuthenticationFilter.java
 * Class:			BaseFormAuthenticationFilter
 * Date:			2012-10-29
 * Author:			<a href="mailto:anycc@gmail.com">anycc</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.datababys.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.datababys.util.Exceptions;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.google.gson.Gson;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;


/**
 * 	
 * @author 	<a href="mailto:anycc@gmail.com">anycc</a>
 * Version  1.1.0
 * @since   2012-10-29 上午9:37:02 
 */

public class BaseFormAuthenticationFilter extends FormAuthenticationFilter {
	private static final Logger log = LoggerFactory.getLogger(BaseFormAuthenticationFilter.class);
	
	/*
	 * 覆盖默认实现，打印日志便于调试，查看具体登录是什么错误。
	 *（可以扩展把错误写入数据库之类的。）
	 * (non-Javadoc)
	 * @see org.apache.shiro.web.filter.authc.FormAuthenticationFilter#onLoginFailure(org.apache.shiro.authc.AuthenticationToken, org.apache.shiro.authc.AuthenticationException, javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request)
				.getHeader("X-Requested-With"))) {// 不是ajax请求
			setFailureAttribute(request, e);
			return true;
		}
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String message = e.getClass().getSimpleName();
			if ("IncorrectCredentialsException".equals(message)) {
				out.println("{\"success\":false,\"message\":\"密码错误\"}");
			} else if ("UnknownAccountException".equals(message)) {
				out.println("{\"success\":false,\"message\":\"账号不存在\"}");
			} else if ("LockedAccountException".equals(message)) {
				out.println("{\"success\":false,\"message\":\"账号被锁定\"}");
			} else {
				out.println("{\"success\":false,\"message\":\"未知错误\"}");
			}
			out.flush();
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   return false;
	}
	
	/**
	 * 覆盖isAccessAllowed，改变shiro的验证逻辑。
	 * 避免不能多次登录的错误。
	 * @param request
	 * @param response
	 * @param mappedValue
	 * @return  
	 * @see org.apache.shiro.web.filter.authc.AuthenticatingFilter#isAccessAllowed(ServletRequest, ServletResponse, Object)
	 */
    @Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		try {
			// 先判断是否是登录操作
			if (isLoginSubmission(request, response)) {
				if (log.isTraceEnabled()) {
					log.trace("Login submission detected.  Attempting to execute login.");
				}
				return false;
			}
		} catch (Exception e) {
			log.error(Exceptions.getStackTraceAsString(e));
		}

		return super.isAccessAllowed(request, response, mappedValue);
	}
    
	/**
	 * 覆盖默认实现，用sendRedirect直接跳出框架，以免造成js框架重复加载js出错。
	 * @param token
	 * @param subject
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception  
	 * @see FormAuthenticationFilter#onLoginSuccess(AuthenticationToken, Subject, ServletRequest, ServletResponse)
	 */
	@Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
            ServletRequest request, ServletResponse response) throws Exception {
		//issueSuccessRedirect(request, response);
		//we handled the success redirect directly, prevent the chain from continuing:
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		
		ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
		// 加入ipAddress
		shiroUser.setIpAddress(request.getRemoteAddr());

		//if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest
		//		.getHeader("X-Requested-With"))) {// 不是ajax请求
		//	issueSuccessRedirect(request, response);
		//} else {
			httpServletResponse.setCharacterEncoding("UTF-8");

   //{'sessionID':'${sessionID}','userId':'${shiroUser.user.id}','username':'${shiroUser.user.username}','org':{'code':'${shiroUser.user.organization.organcode}','pid':'${shiroUser.user.organization.parent.id}','id':'${shiroUser.user.organization.id}','name':'${shiroUser.user.organization.organname}','gbcode':'${shiroUser.user.organization.gbcode}'}}

		    PrintWriter out = httpServletResponse.getWriter();
			out.println("{\"success\":true,\"message\":{\"userId\":\""+shiroUser.getUser().getId()+"\",\"username\":\""+shiroUser.getUser().getUsername()+",\"org\":{\"code\":"+shiroUser.getUser().getOrganization().getOrgancode()+"\",\"pid\":\""+shiroUser.getUser().getOrganization().getId()+"\",\"name\":\""+shiroUser.getUser().getOrganization().getOrganname()+"\"},"+"\"gbcode\":\""+shiroUser.getUser().getOrganization().getGbcode()+"\"}");
			out.flush();
			out.close();
		//}
		
		return false;
	}

	/** * 表示当访问拒绝时 * @param request * @param response * @return * @throws Exception */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if(this.isLoginRequest(request, response)) {
			if(this.isLoginSubmission(request, response)) {
				if(log.isTraceEnabled()) {
					log.trace("Login submission detected. Attempting to execute login.");
				}

				return this.executeLogin(request, response);
			} else {
				if(log.isTraceEnabled()) {
					log.trace("Login page view.");
				}

				return true;
			}
		} else {
			if(log.isTraceEnabled()) {
				log.trace("Attempting to access a path which requires authentication. Forwarding to the Authentication url [" + this.getLoginUrl() + "]");
			}

			this.saveRequestAndRedirectToLogin(request, response);
			return false;
		}
	}


}
