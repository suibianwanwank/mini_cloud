package com.ccsu.filter;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public class MyAuthenticationFilter extends AuthenticationWebFilter {
    MySecurityContextRepository mySecurityContextRepository;


    public MyAuthenticationFilter(MySecurityContextRepository mySecurityContextRepository) {
        super(mySecurityContextRepository.myAuthenticationManager);
        this.mySecurityContextRepository=mySecurityContextRepository;
    }

    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        WebFilterExchange webFilterExchange=new WebFilterExchange(exchange,chain);
        Mono<Authentication> map = mySecurityContextRepository.load(exchange).map(SecurityContext::getAuthentication);

        return map.hasElement().flatMap(hasElement -> {
            if (!hasElement) {
                return onAuthenticationFailure(webFilterExchange);
            } else {
                return map.flatMap(authentication -> {
                    return onAuthenticationSuccess(authentication,webFilterExchange);

                });
            }


    });
    }

    @Override
    protected Mono<Void> onAuthenticationSuccess(Authentication authentication, WebFilterExchange webFilterExchange) {
        return Mono.defer(() -> Mono
                .just(webFilterExchange.getExchange().getResponse())
                .flatMap(response -> {
                    DataBufferFactory dataBufferFactory = response.bufferFactory();

                    // 生成JWT token
                    System.out.println("返回成功");
                    byte[] bytes="认证成功".getBytes(StandardCharsets.UTF_8);
                    response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                    DataBuffer buffer = response.bufferFactory().wrap(bytes);
                    return response.writeWith(Mono.just(buffer));

                }));
    }



    protected Mono<Void> onAuthenticationFailure( WebFilterExchange webFilterExchange) {
        return Mono.defer(() -> Mono
                .just(webFilterExchange.getExchange().getResponse())
                .flatMap(response -> {
                    DataBufferFactory dataBufferFactory = response.bufferFactory();

                    // 生成JWT token
                    System.out.println("返回失败");
                    byte[] bytes="认证失败".getBytes(StandardCharsets.UTF_8);
//                    response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                    response.beforeCommit(() -> {
                        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                        return Mono.empty();
                    });
                    DataBuffer buffer = response.bufferFactory().wrap(bytes);
                    return response.writeWith(Mono.just(buffer));

                }));
    }

}


