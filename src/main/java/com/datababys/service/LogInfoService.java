/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service;


import com.datababys.entity.main.LogInfo;
import com.datababys.util.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface LogInfoService {
	LogInfo get(Long id);

	void saveOrUpdate(LogInfo logInfo);

	void delete(Long id);
	
	List<LogInfo> findAll(Page page);
	
	List<LogInfo> findByExample(Specification<LogInfo> specification, Page page);
}
