package com.datababys.controller;


import com.datababys.shiro.ShiroUser;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lovme on 2017/2/14.
 */
@Controller
public class LoginController {

    private static final String LOGIN_PAGE = "login";
    private static final String LOGIN_DIALOG = "management/index/loginDialog";

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String username,String password,boolean rememeberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(rememeberMe);
        Subject currentUser = SecurityUtils.getSubject();
        String ret = "";
        try {
            currentUser.login(token);

            ret = "{success:true,message:'登陆成功'}";
        } catch (UnknownAccountException ex) {
            ret = "{success:false,message:'账号错误'}";

        } catch (IncorrectCredentialsException ex) {
            ret = "{success:false,message:'密码错误'}";

        } catch (LockedAccountException ex) {
            ret = "{success:false,message:'账号已被锁定，请与管理员联系'}";

        } catch (AuthenticationException ex) {
            ret = "{success:false,message:'您没有授权'}";

        }

        return com.datababys.util.SecurityUtils.getShiroUser().getAttribute("roleNames").toString();
    }

}
