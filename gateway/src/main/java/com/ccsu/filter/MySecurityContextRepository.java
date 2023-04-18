package com.ccsu.filter;


import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Component
public class MySecurityContextRepository implements ServerSecurityContextRepository {

    @Resource
    MyAuthenticationManager myAuthenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        System.out.println("调用save方法");
        return null;
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {


        //load方法要做的事从当前请求中提取出 SecurityContext 对象并存储到对应的上下文中，
        // 以便后续的请求能够共享这个上下文信息。例如，在一个已经通过身份验证的用户访问受保护的资源时，
        // ServerSecurityContextRepository 可以从当前请求中提取出用户的 Authentication 对象，
        // 并存储到 SecurityContext中

        HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
        System.out.println("做出改变到达load");
        String authorization = httpHeaders.getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(authorization)) {
            return Mono.empty();
        }
        System.out.println(authorization);
        //todo 根据token解析出authorization
        System.out.println("根据token解析出authorization");


        MyUserDetails myUserDetails=new MyUserDetails();
        myUserDetails.setName(authorization);
        //TODO 判断authorization是否正确
        MyAuthenticationToken authToken = new MyAuthenticationToken(myUserDetails);


        Mono<Authentication> authenticate = myAuthenticationManager.authenticate(authToken);

        return authenticate.switchIfEmpty(Mono.empty()).map(authentication -> {

                    SecurityContext securityContext = new SecurityContextImpl();
                    securityContext.setAuthentication(authentication);
                    return securityContext;
                }
        );
    }
}
