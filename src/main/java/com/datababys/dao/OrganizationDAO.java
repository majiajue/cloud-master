/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.dao;


import com.datababys.entity.main.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

public interface OrganizationDAO extends JpaRepository<Organization, Long>, JpaSpecificationExecutor<Organization> {
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true"),
            @QueryHint(name = "org.hibernate.cacheRegion", value = "net.hp.es.adm.healthcare.rphcp.entity.main.Organization") })
    @Query("from Organization o order by o.priority ASC")
    List<Organization> findAllWithCache();

    @Query("FROM Organization o WHERE o.organcode like ?1 ")
    List<Organization> findAllWithOrgancode(String orgcode);

    // @Query("select new net.hp.es.adm.healthcare.rphcp.entity.main.Organization(o.id ,o.organcode,o.organname ) from
    // Organization o where o.id=?1 order by o.priority ASC")
    @Query("FROM Organization o WHERE o.id = ?1 ")
    List<Organization> organizationQuery(Long pid);

    Organization getByOrganname(String organname);

    Organization getByOrgancode(String organcode);

    List<Organization> findByParent_id(Long parent_id);

    List<Organization> findById(Long id);

    @Query("from Organization o where o.area.id = ?1 ")
    List<Organization> getOrgByAreaId(Long id);

    @Query("from Organization o where o.area.gbcode = ?1 ")
    List<Organization> findByAreaGbcode(String code);

    List<Organization> findByOrgType(String orgType);

    @Query("from Organization o where o.parent IS NULL")
    List<Organization> findByParentIsNull();

    // @Query("from Area o where o.name =?2 and o.year=?1 ")
    // List<Area> findByNameAndYear(String name,String year);
}