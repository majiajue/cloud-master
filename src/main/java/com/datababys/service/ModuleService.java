/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service;


import com.datababys.entity.main.Module;
import com.datababys.util.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ModuleService {
	Module get(Long id);

	void saveOrUpdate(Module module);

	void delete(Long id);
	
	List<Module> findAll(Page page);
	
	List<Module> findByExample(Specification<Module> specification, Page page);
	
	Module getTree();
	
	List<Module> findAll();
}
