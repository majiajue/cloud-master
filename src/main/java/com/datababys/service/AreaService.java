/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service;


import com.datababys.entity.main.Area;

import com.datababys.util.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface AreaService {
	Area get(Long id);

	void saveOrUpdate(Area area);

	void delete(Long id);
	
	List<Area> findAll(Page page);
	
	Area getTree(String year);
	
	Area  getAreaRoot(String year);
	
	List<Area> findByExample(Specification<Area> specification, Page page);

	List<Area> findByExample(Specification<Area> specification);

	List<Area> findByParentAndYear(Area parent, String year);
}
