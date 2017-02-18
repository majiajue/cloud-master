/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.dao;



import javax.persistence.QueryHint;


import com.datababys.entity.main.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;


public interface AreaDAO extends JpaRepository<Area, Long>, JpaSpecificationExecutor<Area> {
	@QueryHints(value={
			@QueryHint(name="org.hibernate.cacheable",value="true"),
			@QueryHint(name="org.hibernate.cacheRegion",value="net.hp.es.adm.healthcare.rphcp.entity.main.Area")
		}
	)//根据年份查出area表中所有的区域，并按优先级排序
	@Query("from Area o where o.year=?1 order by o.priority ASC")
	List<Area> findAllWithCache(String year);

	@Query("from Area o where o.year=?1 and o.parent.id is null")
	Area  getAreaRoot(String year);
	
	Area getByName(String name);
	
	//@Query("from Area o where o.name =?2 and o.year=?1 ")
	List<Area> findByNameAndYear(String name, String year);
	
	List<Area> findByIdAndYear(long id, String year);

	List<Area> findByParentAndYear(Area parent, String year);

}