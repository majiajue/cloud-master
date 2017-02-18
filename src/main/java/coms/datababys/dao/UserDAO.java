package coms.datababys.dao;

import coms.datababys.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Lovme on 2017/2/16.
 */
public interface UserDAO extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

}
