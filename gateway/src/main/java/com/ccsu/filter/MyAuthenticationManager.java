package com.ccsu.filter;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Component
public class MyAuthenticationManager implements ReactiveAuthenticationManager {
//    @Resource
//    MyUserDetailService myUserDetailService;
private final static String USER_PREFIX="login:token:";
    @Resource
    StringRedisTemplate stringRedisTemplate;


    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        if (authentication.isAuthenticated()) {
            return Mono.just(authentication);
        }

//        Set<Object> keys = stringRedisTemplate.opsForHash().keys(authentication.getPrincipal().toString());
        if(authentication.getName().equals("")){
            System.out.println("返回了？");
            return Mono.empty();
        }
        MyUserDetails userDetails = new MyUserDetails();
        if(!stringRedisTemplate.opsForHash().hasKey(USER_PREFIX + authentication.getName(),"username")){

            return Mono.empty();
        }
        String username = stringRedisTemplate.opsForHash().get(USER_PREFIX + authentication.getName(), "username").toString();

        userDetails.setName(username);


        MyAuthenticationToken authenticationToken = new MyAuthenticationToken(userDetails);
        authenticationToken.setAuthenticated(true);
        System.out.println("Manager返回结果成功");
        return Mono.just(authenticationToken);

    }
}
