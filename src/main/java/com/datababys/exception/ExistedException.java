/**
 * <pre>
 * Copyright:		Copyright(C) 2013-2015, anycc.com
 * Filename:		net.hp.es.adm.healthcare.rphcp.exception.ExistedException.java
 * Class:			ExistedException
 * Date:			2012-8-13
 * Author:			<a href="mailto:anycc@gmail.com">anycc</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.datababys.exception;

/** 
 * 	
 * @author 	<a href="mailto:anycc@gmail.com">anycc</a>
 * Version  1.1.0
 * @since   2012-8-13 上午10:54:15 
 */

public class ExistedException extends ServiceException {

	/** 描述  */
	private static final long serialVersionUID = -598071452360556064L;

	public ExistedException() {
		super();
	}

	public ExistedException(String message) {
		super(message);
	}

	public ExistedException(Throwable cause) {
		super(cause);
	}

	public ExistedException(String message, Throwable cause) {
		super(message, cause);
	}
}
