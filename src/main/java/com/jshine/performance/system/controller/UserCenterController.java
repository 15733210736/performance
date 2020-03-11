package com.jshine.performance.system.controller;

import com.jshine.performance.system.model.UserBean;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Controller
public class UserCenterController {
    //个人中心，需认证可访问
    @RequestMapping("/user/index")
    public String add(HttpServletRequest request){
        UserBean bean = (UserBean) SecurityUtils.getSubject().getPrincipal();
        List ll=new LinkedList();
        request.setAttribute("userName", bean.getName());
        return "/user/index";
    }
    //会员中心，需认证且角色为vip可访问
    @RequestMapping("/vip/index")
    public String update(){
        return "/vip/index";
    }
}
