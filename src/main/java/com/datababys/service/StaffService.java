package com.datababys.service;


import com.datababys.entity.main.Staff;
import com.datababys.util.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface StaffService {
	Staff get(Long id);

	void saveOrUpdate(Staff staff);

	void delete(Long id);
	
	List<Staff> findAll(Page page);
	
	List<Staff> findByExample(Specification<Staff> specification, Page page);
	
	List<Staff> staffQuery(Long id);

	Staff getByWorkCardId(String workCardId);  
	
	Staff getByCardNum(String cardNum);	
	
	List<Staff> getStaffByDepId(Long id);
	
	/**
	 * 查询全部staff列表
	 * @return
	 */
	List<Staff> findList();
	
}
