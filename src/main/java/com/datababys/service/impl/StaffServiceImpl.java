package com.datababys.service.impl;


import com.datababys.dao.StaffDAO;
import com.datababys.entity.main.Staff;
import com.datababys.service.StaffService;
import com.datababys.util.Page;
import com.datababys.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
	
	@Autowired
	private StaffDAO staffDAO;


	@Override
	public Staff get(Long id) {
		// TODO Auto-generated method stub
		return staffDAO.findOne(id);
	}
	
	@Override
	public Staff getByWorkCardId(String workCardId) {
		return staffDAO.getByWorkCardId(workCardId);
	}
	
	@Override
	public Staff getByCardNum(String cardNum) {
		return staffDAO.getByCardNum(cardNum);
	}

	@Override
	public void saveOrUpdate(Staff staff) {
		if (staff.getId() == null) {
		/*
		    if (staffDAO.getByWorkCardId(staff.getWorkCardId()) != null) {
			    throw new NotExistedException(staff.getWorkCardId() + "已存在！");
		    }
		    if (staffDAO.getByCardNum(staff.getCardNum()) != null) {
			    throw new NotExistedException(staff.getCardNum() + "已存在！");
		    }*/
		}
		staffDAO.save(staff);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		staffDAO.delete(id);
	}

	@Override
	public List<Staff> findAll(Page page) {
		// TODO Auto-generated method stub
		org.springframework.data.domain.Page<Staff> springDataPage = staffDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	@Override
	public List<Staff> findByExample(Specification<Staff> specification,
			Page page) {
		org.springframework.data.domain.Page<Staff> springDataPage = staffDAO.findAll(specification, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	@Override
	public List<Staff> staffQuery(Long id) {
		return staffDAO.staffQuery(id);
	}

	@Override
	public List<Staff> getStaffByDepId(Long id) {
		
		return staffDAO.getStaffByDepId(id );
	}

	/**
	 * 查询全部staff列表
	 * @return
	 */
	public List<Staff> findList(){
		return staffDAO.findAll();
	}

}
