package com.jshine.performance.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;

public class LoginMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String pwd = jshineEncrypt(String.valueOf(usernamePasswordToken.getPassword()));
        String mysqlpwd = (String) info.getCredentials();
        return this.equals(pwd, mysqlpwd);
    }
    public String jshineEncrypt(String value){
        return new SimpleHash("MD5",value,"jshine",1024).toString();
    }
}
