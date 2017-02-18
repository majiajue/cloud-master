/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.dao;


import com.datababys.entity.main.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	User getByUsername(String username);
	User getByPhone(String username);
	User getByEmail(String username);

	List<User> findByOrganizationId(Long id);
}