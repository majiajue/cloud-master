/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service.impl;


import com.datababys.dao.AreaDAO;
import com.datababys.entity.main.Area;
import com.datababys.exception.NotDeletedException;
import com.datababys.service.AreaService;
import com.datababys.util.Page;
import com.datababys.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AreaServiceImpl implements AreaService {
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private AreaDAO areaDAO;

	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.AreaService#get(java.lang.Long)  
	 */ 
	@Override
	public Area get(Long id) {
		return areaDAO.findOne(id);
	}



	@Override
	public Area getTree(String year) {
		List<Area> list = areaDAO.findAllWithCache(year);
		
		List<Area> rootList = makeTree(list);
				
		return rootList.get(0);
	}
	
	private List<Area> makeTree(List<Area> list) {
		List<Area> parent = new ArrayList<Area>();
		// get parentId = null;
		for (Area e : list) {
			if (e.getParent() == null) {
				e.setChildren(new ArrayList<Area>(0));
				parent.add(e);
			}
		}
		// 删除parentId = null;
		list.removeAll(parent);
		
		makeChildren(parent, list);
		
		return parent;
	}
	
	
	private void makeChildren(List<Area> parent, List<Area> children) {
		if (children.isEmpty()) {
			return ;
		}
		List<Area> tmp = new ArrayList<Area>();
		for (Area c1 : parent) {
			for (Area c2 : children) {
				c2.setChildren(new ArrayList<Area>(0));
				if (c1.getId().equals(c2.getParent().getId())) {
					c1.getChildren().add(c2);
					tmp.add(c2);
				}
			}
		}
		
		children.removeAll(tmp);
		
		makeChildren(tmp, children);
	}
	
	/*
	 * (non-Javadoc) 
	 * @see net.hp.es.adm.healthcare.rphcp.service.AreaService#saveOrUpdate(net.hp.es.adm.healthcare.rphcp.entity.Area)  
	 */
	@Override
	public void saveOrUpdate(Area area) {
		em.clear();
		areaDAO.save(area);
	}

	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.AreaService#delete(java.lang.Long)  
	 */
	@Override
	public void delete(Long id) {
		if (isRoot(id)) {
			throw new NotDeletedException("不允许删除区划。");
		}
		Area area = this.get(id);
		
		//先判断是否存在子模块，如果存在子模块，则不允许删除
		if(area.getChildren().size() > 0){
			throw new NotDeletedException(area.getName() + "区划下存在子区划，不允许删除。");
		}
		
		areaDAO.delete(area);
	}



	/**
	 * 判断是否是区划.
	 */
	private boolean isRoot(Long id) {
		return id == 1;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.AreaService#findAll(net.hp.es.adm.healthcare.rphcp.util.dwz.Page)  
	 */
	@Override
	public List<Area> findAll(Page page) {
		org.springframework.data.domain.Page<Area> springDataPage = areaDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.hp.es.adm.healthcare.rphcp.service.AreaService#findByExample(org.springframework.data.jpa.domain.Specification, net.hp.es.adm.healthcare.rphcp.util.dwz.Page)	
	 */
	@Override
	public List<Area> findByExample(
			Specification<Area> specification, Page page) {
		org.springframework.data.domain.Page<Area> springDataPage = areaDAO.findAll(specification, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
	@Override
	public List<Area> findByExample(Specification<Area> specification) {
		List<Area> springDataPage = areaDAO.findAll(specification);
		return springDataPage;
	}

	@Override
	public List<Area> findByParentAndYear(Area parent, String year) {
		List<Area> areas = areaDAO.findByParentAndYear(parent, year);
		return areas;
	}

	public Area  getAreaRoot(String year){
		Area area=areaDAO.getAreaRoot(year);
		return area;
	}


}
