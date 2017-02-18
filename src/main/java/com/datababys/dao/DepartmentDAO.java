/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.dao;


import com.datababys.entity.main.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

public interface DepartmentDAO extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department> {
	@QueryHints(value={
			@QueryHint(name="org.hibernate.cacheable",value="true"),
			@QueryHint(name="org.hibernate.cacheRegion",value="net.hp.es.adm.healthcare.rphcp.entity.main.Department")
		}
	)
	
	@Query("from Department o ")
	List<Department> findAllWithCache();
	
	
	//@Query("from Department d,Organization o where d.organization.id = o.id and o.id =?1  ")
    @Query("from Department d where d.organization.id =?1 and d.parent.id=?2 ")
	List<Department> departmentQuery(Long id, Long id1);
	
	/*@Query("from Department d,Oragnization o on d.organization.id = o.id where o.id =?1  ")
	List<Staff> stafQuery(Long id);*/
	
	Department getByDepartmentname(String depname);
	
	Department getById(Long id);
	
    @Query("from Department a where a.organization.id =?1 ")
	List<Department> getDeptByOrgId(Long id);

}