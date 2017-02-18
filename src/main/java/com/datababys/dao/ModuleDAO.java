/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.dao;


import com.datababys.entity.main.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

public interface ModuleDAO extends JpaRepository<Module, Long>, JpaSpecificationExecutor<Module> {
	Page<Module> findByParentId(Long parentId, Pageable pageable);
	
	@QueryHints(value={
			@QueryHint(name="org.hibernate.cacheable",value="true"),
			@QueryHint(name="org.hibernate.cacheRegion",value="net.hp.es.adm.healthcare.rphcp.entity.main.Module")
		}
	)
	@Query("from Module m order by m.priority ASC")//查询出module表里的模块并升序排序
	List<Module> findAllWithCache();
	
	Module getBySn(String sn);
}