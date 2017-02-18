/**
 * <pre>
 * Copyright:		Copyright(C) 2013-2015, anycc.com
 * Filename:		net.hp.es.adm.healthcare.rphcp.shiro.IncorrectCaptchaException.java
 * Class:			IncorrectCaptchaException
 * Date:			2012-8-7
 * Author:			<a href="mailto:anycc@gmail.com">anycc</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/

package com.datababys.shiro;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 
 * @author <a href="mailto:anycc@gmail.com">anycc</a> Version 1.1.0
 * @since 2012-8-7 上午9:22:21
 */

public class IncorrectCaptchaException extends AuthenticationException {
	/** 描述  */
	private static final long serialVersionUID = 6146451562810994591L;

	public IncorrectCaptchaException() {
		super();
	}

	public IncorrectCaptchaException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectCaptchaException(String message) {
		super(message);
	}

	public IncorrectCaptchaException(Throwable cause) {
		super(cause);
	}

}
