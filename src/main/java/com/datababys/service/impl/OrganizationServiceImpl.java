/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service.impl;


import com.datababys.common.dto.DTPager;
import com.datababys.dao.OrganizationDAO;
import com.datababys.dao.UserDAO;
import com.datababys.entity.main.Organization;
import com.datababys.exception.NotDeletedException;
import com.datababys.exception.NotExistedException;
import com.datababys.service.OrganizationService;
import com.datababys.util.Page;
import com.datababys.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationDAO organizationDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    @PersistenceContext
    private EntityManager em;

    /*
     * (non-Javadoc)
     * 
     * @see net.hp.es.adm.healthcare.rphcp.service.OrganizationService#get(java.lang.Long)
     */
    @Override
    public Organization get(Long id) {
        return organizationDAO.findOne(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.hp.es.adm.healthcare.rphcp.service.OrganizationService#getByName(java.lang.String)
     */
    @Override
    public Organization getByOrganname(String organname) {
        return organizationDAO.getByOrganname(organname);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.hp.es.adm.healthcare.rphcp.service.OrganizationService#saveOrUpdate(net.hp.es.adm.healthcare.rphcp.entity.
     * main.Organization)
     */
    @Override
    public void saveOrUpdate(Organization organization) {
        System.out.println("|||||||||||||||||12||" + organization.getOrgancode());
        em.clear();
        if (organization.getId() == null) {
            Organization parentOrganization = organizationDAO.findOne(organization.getParent().getId());
            // organization.setId("1234");
            if (parentOrganization == null) {
                throw new NotExistedException("id=" + organization.getParent().getId() + "父组织不存在！");
            }
            if (organizationDAO.getByOrganname(organization.getOrganname()) != null) {
                throw new NotExistedException(organization.getOrganname() + "已存在！");
            }
        }

        /*
         * String s = organization.getOrgancode(); System.out.println("LLL "+"".equals(s)); if("".equals(s)){ //creat 时
         * Organcode System.out.println("LL "+organization.getParent().getOrgancode()); Long parent_id =
         * organization.getParent().getId(); System.out.println("LL "+parent_id); //自增1 List<Organization> list =
         * organizationDAO.findByParent_id(parent_id); String code =""; if(list.size()>0){ System.out.println("LL1L "
         * +list); String max = list.get(0).getOrgancode(); for(int i=1;i<list.size();i++){ String aa =
         * list.get(i).getOrgancode(); int result = max.compareTo(aa); if(result<0){ max = aa; } } Long code0 =
         * Long.parseLong(max)+1; code = Long.toString(code0) ; if(code.length()<max.length()){ code = "0"+code; }
         * }else{ List<Organization> lista = organizationDAO.findById(organization.getParent().getId()); code =
         * lista.get(0).getOrgancode() +"01" ; } System.out.println("LLcode "+code); organization.setOrgancode(code); }
         */
        // area
        /*
         * List<Organization> lista = organizationDAO.findById(organization.getParent().getId()); String organname =
         * lista.get(0).getOrganname(); Calendar a=Calendar.getInstance(); String year =
         * String.valueOf(a.get(Calendar.YEAR)); List<Area> listb = areaDAO.findByNameAndYear(organname, year); String
         * zonegb = listb.get(0).getZonegb(); String gbcode = listb.get(0).getGbcode(); organization.setZonegb(zonegb);
         * organization.setGbcode(gbcode);
         */

        organizationDAO.save(organization);
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.hp.es.adm.healthcare.rphcp.service.OrganizationService#delete(java.lang.Long)
     */
    @Override
    public void delete(Long id) {
        if (isRoot(id)) {
            throw new NotDeletedException("不允许删除根组织。");
        }

        Organization organization = this.get(id);

        // 先判断是否存在子模块，如果存在子模块，则不允许删除
        if (organization.getChildren().size() > 0) {
            throw new NotDeletedException(organization.getOrganname() + "组织下存在子组织，不允许删除。");
        }

        if (userDAO.findByOrganizationId(id).size() > 0) {
            throw new NotDeletedException(organization.getOrganname() + "组织下存在用户，不允许删除。");
        }

        organizationDAO.delete(organization);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.hp.es.adm.healthcare.rphcp.service.OrganizationService#findAll(net.hp.es.adm.healthcare.rphcp.util.dwz.Page)
     */
    @Override
    public List<Organization> findAll(Page page) {
        org.springframework.data.domain.Page<Organization> springDataPage = organizationDAO
                .findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.hp.es.adm.healthcare.rphcp.service.OrganizationService#findByExample(org.springframework.data.jpa.domain.
     * Specification, net.hp.es.adm.healthcare.rphcp.util.dwz.Page)
     */
    @Override
    public List<Organization> findByExample(Specification<Organization> specification, Page page) {
        org.springframework.data.domain.Page<Organization> springDataPage = organizationDAO.findAll(specification,
                PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.hp.es.adm.healthcare.rphcp.service.OrganizationService#findByExample(org.springframework.data.jpa.domain.
     * Specification, net.hp.es.adm.healthcare.rphcp.util.dwz.Page)
     */
    @Override
    public org.springframework.data.domain.Page<Organization> findByExample(Specification<Organization> specification,
            DTPager page) {
        org.springframework.data.domain.Page<Organization> springDataPage = organizationDAO.findAll(specification,
                PageUtils.createPageable(page));
        return springDataPage;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.hp.es.adm.healthcare.rphcp.service.OrganizationService#getTree()
     */
    @Override
    public Organization getTree() {
        List<Organization> list = organizationDAO.findAllWithCache();

        List<Organization> rootList = makeTree(list);

        return rootList.get(0);
    }

    /*
     * old one
     * 
     * @Override public Organization getTree(Long id) { Organization org=get(id); List<Organization> list =
     * organizationDAO.findAllWithOrgancode(org.getOrgancode()+"%"); List<Organization> rootList = makeTree(list,id);
     * return rootList.get(0); }
     */

    @Override
    public Organization getTree(Long id) {
        List<Organization> list = organizationDAO.organizationQuery(id);
        return list.get(0);
    }

    /**
     * 判断是否是根组织.
     */
    private boolean isRoot(Long id) {
        return id.equals(1);
    }

    private List<Organization> makeTree(List<Organization> list) {
        List<Organization> parent = new ArrayList<Organization>();
        // get parentId = null;
        for (Organization e : list) {
            if (e.getParent() == null) {
                e.setChildren(new ArrayList<Organization>(0));
                parent.add(e);
            }
        }
        // 删除parentId = null;
        list.removeAll(parent);

        makeChildren(parent, list);

        return parent;
    }

    private void makeChildren(List<Organization> parent, List<Organization> children) {
        if (children.isEmpty()) {
            return;
        }

        List<Organization> tmp = new ArrayList<Organization>();
        for (Organization c1 : parent) {
            for (Organization c2 : children) {
                c2.setChildren(new ArrayList<Organization>(0));
                if (c1.getId().equals(c2.getParent().getId())) {
                    c1.getChildren().add(c2);
                    tmp.add(c2);
                }
            }
        }

        children.removeAll(tmp);

        makeChildren(tmp, children);
    }

    @Override
    public List<Organization> findList() {
        List<Organization> orgList = organizationDAO.findAll();
        return orgList;
    }

    @Override
    public List<Organization> findListById(String orgCode) {
        String hql = "SELECT new net.hp.es.adm.healthcare.rphcp.entity.main.Organization(id,organname,organcode,longitude,latitude,orgType,gbcode,zonegb,genrecode,managecode,address,tel) FROM Organization A ";
        if (!"321000".equals(orgCode.substring(0, 6))) {
            hql += " where organcode like '" + orgCode.substring(0, 6) + "%'";
        }
        TypedQuery<Organization> query = em.createQuery(hql, Organization.class);
        return query.getResultList();
    }

    @Override
    public List<Organization> getOrgByAreaId(Long id) {

        return organizationDAO.getOrgByAreaId(id);
    }

    @Override
    public List<Organization> queryByAreaCode(String code) {
        return organizationDAO.findByAreaGbcode(code);
    }

    @Override
    public List<Organization> findListByOrgType(String orgType) {
        String hql = "SELECT new net.hp.es.adm.healthcare.rphcp.entity.main.Organization(id,organname,organcode,longitude,latitude,orgType,gbcode,zonegb,genrecode,managecode,address,tel) FROM Organization A "
                + " where orgType=(:orgType)";
        TypedQuery<Organization> query = em.createQuery(hql, Organization.class);
        query.setParameter("orgType", orgType);
        return query.getResultList();
    }

    @Override
    public List<Organization> queryByParentIsNull() {
        return organizationDAO.findByParentIsNull();
    }
}
