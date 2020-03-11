package com.jshine.performance.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.jshine.performance.utils.SpringUtil;
import com.jshine.performance.utils.TokenUtil;
import com.jshine.performance.utils.message.CodeEnum;
import com.jshine.performance.utils.message.ReturnMessageUtil;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JwtUserFilter extends UserFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (this.isLoginRequest(request, response)) {
            //登录请求返回true
            return true;
        } else {
            //非登录请求处理
            /*Subject subject = this.getSubject(request, response);
            return subject.getPrincipal() != null;*/
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            String token = httpRequest.getHeader("token");
            if(StringUtils.isEmpty(token)){
                token = httpRequest.getParameter("token");
            }
            if(!StringUtils.isEmpty(token)){
            TokenUtil tokenUtil = SpringUtil.getBean(TokenUtil.class);
               /* ReturnMessageUtil.error();*/
            /*try {
                response.getWriter().write("");
            } catch (IOException e) {
                e.printStackTrace();
            }*/
                return !tokenUtil.isTokenExpired(token);
            }
            return false;
        }
    }
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
      /*  HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        if(httpServletRequest.getHeader("X-Requested-With")!=null){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print("你没有登录,跳转到登录页面");
      */      //response.getWriter().print(new JSONObject().put("msg","你没有登录,跳转到登录页面"));
            //response.getWriter().write(new JSONObject().put("msg","你没有登录,跳转到登录页面").toString());
       /* }else{
            this.saveRequestAndRedirectToLogin(request, response);
        }*/

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(JSON.toJSON(ReturnMessageUtil.error(CodeEnum.UNAUTHORIZED)).toString());
        return false;
        /*this.saveRequestAndRedirectToLogin(request, response);
        return false;*/
    }
}
