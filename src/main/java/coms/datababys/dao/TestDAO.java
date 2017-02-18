package coms.datababys.dao;

import coms.datababys.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * Created by Lovme on 2017/2/15.
 */

public interface TestDAO extends JpaRepository<TestEntity, Long>, JpaSpecificationExecutor<TestEntity> {

}