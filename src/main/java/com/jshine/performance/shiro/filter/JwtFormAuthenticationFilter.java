package com.jshine.performance.shiro.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class JwtFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        /*if (this.isLoginRequest(request, response)) {
            if (this.isLoginSubmission(request, response)) {
                return this.executeLogin(request, response);
            } else {
                return true;
            }
        } else {
            HttpServletRequest httpServletRequest = (HttpServletRequest)request;
            if(httpServletRequest.getHeader("X-Requested-With")!=null){
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().print("你没有登录,跳转到登录页面");
            }else{
                this.saveRequestAndRedirectToLogin(request, response);
            }
            return false;
        }*/
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print("你没有登录,跳转到登录页面");
        return false;
    }
}
