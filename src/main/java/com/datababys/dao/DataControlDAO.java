/**
 * There are <a href="https://github.com/anycc/nova">nova</a> code generation
 */
package com.datababys.dao;


import com.datababys.entity.main.DataControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DataControlDAO extends JpaRepository<DataControl, Long>, JpaSpecificationExecutor<DataControl> {

}