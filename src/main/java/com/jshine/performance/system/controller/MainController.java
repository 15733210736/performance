package com.jshine.performance.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.jshine.performance.utils.TokenUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
    用户登录、退出、错误页面跳转控制器
 **/
@Controller
public class MainController {

    @Autowired
    private TokenUtil tokenUtil;
    @RequestMapping("/main")
    public String index(HttpServletRequest request, HttpServletResponse response){
        String token = request.getParameter("token");
        String userName = tokenUtil.getUsernameFromToken(token);
        response.setHeader("root", request.getContextPath());
        return "/page/index";
    }
    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("root", request.getContextPath());
        return "/page/login";
    }
    @RequestMapping("/login")
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("root", request.getContextPath());
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try{
            subject.login(token);
        } catch (UnknownAccountException e){
            e.printStackTrace();
            request.setAttribute("msg","用户名不存在！");
        } catch (IncorrectCredentialsException e){
            request.setAttribute("msg","密码错误！");
        }
        if(subject.isAuthenticated()){
            String jwtToken = tokenUtil.generateToken(userName);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token",jwtToken);
            /*Cookie cookie = new Cookie("token",jwtToken);
            cookie.setHttpOnly(true);//只能通过http访问，javascript无法访问cookie内容
            cookie.setPath("/");//在根目录下的所有应用共享cookie
            cookie.setMaxAge(5*3600);//seconds
            response.addCookie(cookie);
            response.flushBuffer();
            return "redirect:/main";*/
            /*ModelAndView mv = new ModelAndView();
            mv.addObject("result",jsonObject);
            mv.setViewName("/page/index");*/
            return jsonObject;
        }
        //return "/page/login";
        return null;
    }
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "redirect:/toLogin";
    }
    @RequestMapping("/error/unAuth")
    public String unAuth(){
        return "/error/unAuth";
    }
    //测试list

}
