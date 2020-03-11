package com.jshine.performance.system.JwtController;

import com.jshine.performance.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class JwtLoginController {
    @Autowired
    private TokenUtil tokenUtil;
    @RequestMapping("/jwtLogin")
    public String JwtLogin(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String token = tokenUtil.generateToken(userName);
        return token;
    }
}
