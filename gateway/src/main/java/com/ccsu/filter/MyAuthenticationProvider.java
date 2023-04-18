package com.ccsu.filter;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class MyAuthenticationProvider implements AuthenticationProvider {



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("执行认证流程provider");
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
