package coms.datababys.service.impl;

import coms.datababys.dao.UserDAO;
import coms.datababys.entity.UserEntity;
import coms.datababys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Lovme on 2017/2/16.
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    public List<UserEntity> userFind(){
      System.out.println("进入了userFind方法");
        return userDAO.findAll();
    }

}
