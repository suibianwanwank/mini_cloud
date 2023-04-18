package com.ccsu.filter;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 认证成功处理器
 * @Desc 在此进行登录成功后，生成 token 等操作。
 * @Author DaMai
 * @Date 2021/3/23 15:26
 * 但行好事，莫问前程。
 */
@Component
public class MyAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {



    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
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
}
