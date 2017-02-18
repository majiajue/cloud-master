package coms.datababys.controller;

import coms.datababys.entity.UserEntity;
import coms.datababys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Lovme on 2017/2/16.
 */
@RestController
@RequestMapping(value="/management/user")
public class UserContoller {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/user")
    public List<UserEntity> userFind(){
        return userService.userFind();
    }
}
