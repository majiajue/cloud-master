package com.datababys.dao;


import com.datababys.entity.main.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StaffDAO extends JpaRepository<Staff, Long>, JpaSpecificationExecutor<Staff> {
	//User getByUsername(String username);

	
	Staff getByWorkCardId(String workCardId);  
	
	Staff getByCardNum(String cardNum);	
	
    @Query("from Staff a where a.department.id  in(select b.id from Department b,Organization c where b.organization.id=c.id  and c.id=?1)")
	List<Staff> staffQuery(Long id);
    
    @Query("from Staff a where a.department.id =?1 ")
	List<Staff> getStaffByDepId(Long id);

}

