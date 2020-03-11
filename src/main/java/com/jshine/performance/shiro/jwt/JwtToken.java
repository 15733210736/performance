package com.jshine.performance.shiro.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken implements AuthenticationToken {

    private String principal;
    private String token;

    @Override
    public String getPrincipal() {
        return principal;
    }

    @Override
    public String getCredentials() {
        return token;
    }
}
