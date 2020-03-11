package com.jshine.performance.shiro.filter;

import com.jshine.performance.utils.SpringUtil;
import com.jshine.performance.utils.TokenUtil;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JwtAuthenticationFilter extends FormAuthenticationFilter {

    /*@Autowired
    public TokenUtil tokenUtil;*/

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        /**
         * isAccessAllowed如果请求允许正常处理，返回true。
         * 返回false由方法onAccessDenied进行处理请求。
         * 即isAccessAllowed返回true表示用户已登录过，false表示用户还未登录。
         **/
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader("token");
        if(StringUtils.isEmpty(token)){
            token = httpRequest.getParameter("token");
        }
        if(!StringUtils.isEmpty(token)){
            TokenUtil tokenUtil = SpringUtil.getBean(TokenUtil.class);
            /*try {
                response.getWriter().write("");
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            return !tokenUtil.isTokenExpired(token);
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }

}
