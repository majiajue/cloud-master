/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service.impl;


import com.datababys.dao.DepartmentDAO;
import com.datababys.dao.OrganizationDAO;
import com.datababys.entity.main.Department;
import com.datababys.entity.main.Organization;
import com.datababys.exception.NotDeletedException;
import com.datababys.service.DepartmentService;
import com.datababys.util.Page;
import com.datababys.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentDAO departmentDAO;
	
	@Autowired
	private OrganizationDAO organizationDAO;
	

	@Override
	public Department get(Long id) {
		// TODO Auto-generated method stub
		return departmentDAO.findOne(id);
	}

	@Override
	public void saveOrUpdate(Department department) {
		// TODO Auto-generated method stub
		departmentDAO.save(department);
	}
	
	/**
	 * 判断是否是根组织.
	 */
	private boolean isRoot(Long id) {
		return id.equals(1) ;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		if (isRoot(id)) {
			throw new NotDeletedException("不允许删除区划。");
		}
		Department department = this.get(id);
		
		//先判断是否存在子模块，如果存在子模块，则不允许删除
		if(department.getChildren().size() > 0){
			throw new NotDeletedException(department.getDepartmentname() + "区划下存在子区划，不允许删除。");
		}
		
		departmentDAO.delete(department);
		
	}

	@Override
	public List<Department> findList() {
		// TODO Auto-generated method stub
		List<Department> depList = departmentDAO.findAll();
		return depList;
	}

	@Override
	public List<Department> findAll(Page page) {
		org.springframework.data.domain.Page<Department> springDataPage = departmentDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	@Override
	public List<Department> findByExample(
			Specification<Department> specification, Page page) {
		// TODO Auto-generated method stub
		org.springframework.data.domain.Page<Department> springDataPage = departmentDAO.findAll(specification, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	@Override
	public Department getByDepartmentname(String depname) {
		// TODO Auto-generated method stub
		return departmentDAO.getByDepartmentname(depname);
	}

	@Override
	public Department getTree() {
		List<Department> list = departmentDAO.findAllWithCache();
		
		List<Department> rootList = makeTree(list);
				
		return rootList.get(0);
	}

	@Override
	public Department getTree(Long id) {
		// TODO Auto-generated method stub
		Long id1 = 1L;
		List<Department> list = departmentDAO.departmentQuery(id,id1);
		Department dt =new Department();
		dt.setChildren(list);
		dt.setId((long)1);
		dt.setDepartmentname("所有部门");
		//List<Department> rootList = makeTree(list);
		return dt;
	}
	
	private List<Department> makeTree(List<Department> list) {
		List<Department> parent = new ArrayList<Department>();
		// get parentId = null;
		for (Department e : list) {
			if (e.getParent() == null) {
				e.setChildren(new ArrayList<Department>(0));
				parent.add(e);
			}
		}
		// 删除parentId = null;
		list.removeAll(parent);
		
		makeChildren(parent, list);
		
		return parent;
	}
	
	
	private void makeChildren(List<Department> parent, List<Department> children) {
		if (children.isEmpty()) {
			return ;
		}
		List<Department> tmp = new ArrayList<Department>();
		for (Department c1 : parent) {
			for (Department c2 : children) {
				c2.setChildren(new ArrayList<Department>(0));
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
	public List<Organization> organizationQuery(Long id) {
		// TODO Auto-generated method stub
		
		return organizationDAO.organizationQuery(id);
	}

	@Override
	public List<Department> getDeptByOrgId(Long id) {
		return departmentDAO.getDeptByOrgId(id);
	}
	
	
}

