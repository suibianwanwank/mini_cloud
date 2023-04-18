package com.ccsu.filter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserDetails implements UserDetails {

    String name;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("获取权限");
        return null;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }


    @Override
    public String getPassword() {
        System.out.println("getpassword");
        return "ttt";
    }

    @Override
    public String getUsername() {
        System.out.println("getUsername");
        return "aaa";
    }

    @Override
    public boolean isAccountNonExpired() {
        System.out.println("是否sssss");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        System.out.println("是否sss");
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        System.out.println("是否ss");
        return true;
    }

    @Override
    public boolean isEnabled() {
        System.out.println("是否可用");
        return true;
    }
}
