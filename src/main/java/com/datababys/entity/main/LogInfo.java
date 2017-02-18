/**
 * <pre>
 * Copyright:		Copyright(C) 2013-2015, anycc.com
 * Filename:		net.hp.es.adm.healthcare.rphcp.entity.main.LogEntity.java
 * Class:			LogEntity
 * Date:			2013-5-3
 * Author:			<a href="mailto:anycc@gmail.com">anycc</a>
 * Version          2.1.0
 * Description:		
 *
 * </pre>
 **/

package com.datababys.entity.main;

import com.datababys.entity.Idable;
import org.springframework.boot.logging.LogLevel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * 
 * @author <a href="mailto:anycc@gmail.com">anycc</a> Version 2.1.0
 * @since 2013-5-3 下午4:43:44
 */
@Entity
@Table(name = "sys_log_info")
public class LogInfo implements Idable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 32)
	private String username;

	@Column(length = 256)
	private String message;

	@Column(length = 16)
	private String ipAddress;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date createTime;

	@Column(length = 16)
	@Enumerated(EnumType.STRING)
	private LogLevel logLevel;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public LogLevel getLogLevel() {
		return this.logLevel;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
