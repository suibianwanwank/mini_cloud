package com.ccsu.config;

import com.ccsu.filter.MyAuthenticationFilter;
import com.ccsu.filter.MyAuthenticationManager;
import com.ccsu.filter.MyAuthenticationSuccessHandler;
import com.ccsu.filter.MySecurityContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.session.HeaderWebSessionIdResolver;
import org.springframework.web.server.session.WebSessionIdResolver;

import javax.annotation.Resource;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    /**
     * 用户的接口类
     * @return
     */
//    @Bean
//    public MapReactiveUserDetailsService userDetailsService() {
//        //自定义一个用户
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin")
//                .roles("ADMIN")
//                .build();
//        return new MapReactiveUserDetailsService(user);
//    }



    /**
     * 主要过滤配置类
     * @param http
     * @return
     */
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .pathMatchers("/api/login/**").permitAll()
                .pathMatchers("/favicon.ico").permitAll()
//                .pathMatchers("/user/**").hasRole("ADMIN")
                .anyExchange().authenticated()
                .and()
                .addFilterAt(authenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
//                .securityContextRepository(mySecurityContextRepository)
                .csrf().disable().exceptionHandling()
//                .accessDeniedHandler(new AccessDeniedHandler())
//                .accessDeniedHandler()
                .and()
                .cors().configurationSource(corsConfigurationSource());

        return http.build();
    }
    @Resource
    MyAuthenticationManager myAuthenticationManager;


    @Resource
    MySecurityContextRepository mySecurityContextRepository;
    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    private AuthenticationWebFilter authenticationFilter() {
        AuthenticationWebFilter authenticationFilter = new MyAuthenticationFilter(mySecurityContextRepository);
        authenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
//        authenticationFilter.setSecurityContextRepository(mySecurityContextRepository);
//        authenticationFilter.setServerAuthenticationConverter(new ServerFormLoginAuthenticationConverter());
        return authenticationFilter;
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");    //同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
        corsConfiguration.addAllowedHeader("*");//header，允许哪些header，本案中使用的是token，此处可将*替换为token；
        corsConfiguration.addAllowedMethod("*");    //允许的请求方法，PSOT、GET等
        ((UrlBasedCorsConfigurationSource) source).registerCorsConfiguration("/**",corsConfiguration); //配置允许跨域访问的url
        return source;
    }

    @Bean
    public WebSessionIdResolver sessionIdResolver(){
        HeaderWebSessionIdResolver sessionIdResolver=new HeaderWebSessionIdResolver();
        sessionIdResolver.setHeaderName("Authorization");
        return sessionIdResolver;
    }

}
