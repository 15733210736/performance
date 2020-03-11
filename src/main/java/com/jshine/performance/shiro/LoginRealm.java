package com.jshine.performance.shiro;

import com.jshine.performance.system.model.PermissionBean;
import com.jshine.performance.system.model.RoleBean;
import com.jshine.performance.system.model.UserBean;
import com.jshine.performance.system.service.UserService;
import com.jshine.performance.utils.TokenUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenUtil tokenUtil;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
            System.out.println("********执行授权********");
            Subject subject = SecurityUtils.getSubject();
            UserBean user = (UserBean)subject.getPrincipal();
            if(user != null){
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                //角色与权限字符串集合
                Collection<String> rolesCollection = new HashSet<>();
                Collection<String> premissionCollection = new HashSet<>();
                //读取并赋值用户角色与权限
                Set<RoleBean> roles = user.getRoles();
                for(RoleBean role : roles){
                    rolesCollection.add(role.getName());
                    Set<PermissionBean> permissions = role.getPermissions();
                    for (PermissionBean permission : permissions){
                        premissionCollection.add(permission.getUrl());
                    }
                    info.addStringPermissions(premissionCollection);
                }
                info.addRoles(rolesCollection);
                return info;
            }
            return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("********执行认证********");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        UserBean bean = userService.findByName(token.getUsername());
        //JwtToken jwtToken = (JwtToken)authenticationToken;
        //UserBean bean = userService.findByName(tokenUtil.getUsernameFromToken(jwtToken.getToken()));
        if(bean == null){
            throw new UnknownAccountException();
        }
        ByteSource salt = ByteSource.Util.bytes("jshine");
        return new SimpleAuthenticationInfo(bean, bean.getPassword(),
                salt, getName());
    }
    public static void main(String[] args){
        String username = "jack";
        String password = "123456";
        String hashAlgorithName = "MD5";
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes("jshine");
        Object obj = new SimpleHash(hashAlgorithName, password,
                credentialsSalt, hashIterations);
    }
}
