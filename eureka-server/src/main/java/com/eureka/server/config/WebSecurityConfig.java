package com.eureka.server.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * EnableWebSecurity ：表示开启 web 安全验证，继承 WebSecurityConfigurerAdapter ,然后覆盖默认的配置
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 这里的身份验证与这个 CSRF 跨站请求伪造防护是两码事，身份验证指的是登录系统时必须先登录，而 CSRF 防护是要求请求时携带一个 CSRF 令牌。
     * 也就是Spring Security 要求用户访问的请求的时候，必须携带一个 跨站点请求（CSRF）令牌，而显然 Eureka Client 要么就是能提供这个令牌，
     * 要么就是在 Eureka Server 直接忽略掉 Eureka Client 的请求，也就是不对这些请求进行防护。
     * <p>
     * 最后提醒一遍，服务端必须忽略 Eureka Client 的 CSRF 防护，否则客户端会注册不上服务。
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * ignoringAntMatchers(String... antPatterns)：表示对某些请求，如果符合匹配的规则，则忽略对它们的 CSRF 防护
         * /eureka/**：首斜杠表示应用上下文路径，也就是说对所有符合 http://ip:port/context-paht/eureka/** 的请求全部忽略对它们的 CSRF 防护
         * 所以只要 eureka.client.serviceUrl.defaultZone 的值符合此规则，就不会被 CSRF 防护了
         */
        http.csrf().ignoringAntMatchers("/eureka/**");
        /**
         * 还有一个一劳永逸的方式是直接禁止  CSRF 防护:
         * http.csrf().disable();//完全禁止 CSRF 防护
         */
        super.configure(http);
    }
}
