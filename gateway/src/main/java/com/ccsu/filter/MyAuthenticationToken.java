package com.ccsu.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class MyAuthenticationToken extends AbstractAuthenticationToken {
    private Object principal;

    private boolean authenticated = false;

    public MyAuthenticationToken(Object principal) {
        super(null);
        this.principal=principal;
    }

    public MyAuthenticationToken(Collection<? extends GrantedAuthority> authorities,Object principal) {
        super(authorities);
        this.principal=principal;

    }
    @Override
    public String getName(){
        return ((MyUserDetails)principal).getName();
    }

    @Override
    public Object getCredentials() {
        System.out.println("token的getCredentials");
        return null;
    }

    @Override
    public Object getPrincipal() {
        System.out.println("token的getprinc");
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        System.out.println("认证结果为"+principal!=null);
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated=authenticated;
    }
}
