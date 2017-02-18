/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service;


import com.datababys.common.dto.DTPager;
import com.datababys.entity.main.Organization;
import com.datababys.util.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface OrganizationService {
    Organization get(Long id);

    void saveOrUpdate(Organization organization);

    void delete(Long id);

    List<Organization> findList();

    List<Organization> findListByOrgType(String orgType);

    List<Organization> findListById(String orgCode);

    List<Organization> findAll(Page page);

    List<Organization> findByExample(Specification<Organization> specification, Page page);

    org.springframework.data.domain.Page<Organization> findByExample(Specification<Organization> specification,
                                                                     DTPager page);

    Organization getByOrganname(String organname);

    Organization getTree();

    Organization getTree(Long id);

    // List<Organization> organizationQuery(Long id);

    List<Organization> getOrgByAreaId(Long id);

    /**
     * 根据行政区划编码查询组织机构
     * 
     * @param code 行政区划编码
     * @return List<Organization>
     */
    List<Organization> queryByAreaCode(String code);

    /**
     * 查询没有父节点的组织机构
     * 
     * @return Set<Organization>
     */
    List<Organization> queryByParentIsNull();

}
