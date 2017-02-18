/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.service;


import com.datababys.entity.main.User;
import com.datababys.util.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface UserService {
	User get(Long id);

	void saveOrUpdate(User user);

	void delete(Long id);
	
	List<User> findAll(Page page);
	
	List<User> findByExample(Specification<User> specification, Page page);
	
	void updatePwd(User user, String newPwd);
	
	void resetPwd(User user, String newPwd);
	
	User getByUsername(String username);
	
	/**
	 * 查询全部用户列表
	 * @return
	 */
	List<User> findList();
}
