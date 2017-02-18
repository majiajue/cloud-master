package com.datababys.common;

/**
 * 系统常量定义
 * 
 */
public final class Constants {
	/**
	 * 默认日期时间格式
	 */
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";// yyyyMMddHHmmss
	/**
	 * 默认日期格式
	 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";// yyyyMMdd

	/**
	 * Session存储登录用户信息的Key
	 */
	public static final String SESSION_LOGIN_USER = "loginUser";
	
	/**
	 * Session存储登录用户已授权的权限的Key
	 */
	public static final String SESSION_USER_TYPE_RIGHTS = "userTypeRights";
	
	/**
	 * Session存储登录用户未授权的权限的Key
	 */
	public static final String SESSION_USER_TYPE_UNAUTHORIZED = "userTypeUnauthorized";

	/**
	 * 错误信息key
	 */
	public static final String ERROR_MSG = "errorMsg";

	/**
	 * 默认分隔符
	 */
	public static final String SPLIT = ",";
	
	/**
	 *  成功编码
	 */
	public static final String HISDOWN_RESULE_CODE_SUCCESS = "0";
	
	/**
	 * 失败编码
	 */
	public static final String HISDOWN_RESULE_CODE_FAILURE = "1";
	
	/**
	 * 系统错误编码
	 */
	public static final String HISDOWN_RESULE_CODE_SYSFAILURE = "9";
	
	/**
	 * 成功编码
	 */
	public static final String HISLOGINURL_RESULE_CODE_SUCCESS = "0";
	
	/**
	 * 失败编码
	 */
	public static final String HISLOGINURL_RESULE_CODE_FAILURE = "1";
	
	/**
	 * /系统错误编码
	 */
	public static final String HISLOGINURL_RESULE_CODE_SYSFAILURE = "9";
	
	public static final String STORE_DIR = "/upload";
}
