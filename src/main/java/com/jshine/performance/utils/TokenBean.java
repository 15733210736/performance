package com.jshine.performance.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class TokenBean {
    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.expiration}")
    private Long expiration;
}
