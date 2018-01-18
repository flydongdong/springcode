package com.fly.dd.mycode.common.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security配置
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年12月14日 下午4:11:22 $
 */
@Configuration
@EnableWebSecurity
// @EnableWebMvc-->被废弃，自动与MVC结合了
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用权限控制注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private SecurityUserDetailsService securityUserDetailsService;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        log.info("Security初始化===>不拦截规则");
        // 设置不拦截规则
        web.ignoring().antMatchers("/static/**", "/test/**", "/**/*.ftl");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 设置拦截规则
        log.info("Security初始化===>拦截规则");
        http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/**").authenticated()
                // 配置登录页面
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/login_check")
                // failureUrl("/unamenotfind")非转发
                .failureForwardUrl("/unamenotfind").usernameParameter("username").passwordParameter("password")

                .successHandler(customAuthenticationSuccessHandler)
//                .defaultSuccessUrl("/admin")
                // 启用防跨站伪请求攻击，默认启用
                .and().csrf().disable()
                // 配置登出
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll()
                // 启用记住我功能
                .and().rememberMe().rememberMeParameter("remember_me").rememberMeCookieName("remember_me_cookie")
                .tokenValiditySeconds(3600 * 24 * 1)
                // 配置没有权限的跳转(),没有权限的会产生AccessDeniedException异常，可以自定义实现AccessDeniedHandler
                .and().exceptionHandling().accessDeniedPage("/no_auth");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("Security初始化==>配置自定义UserDetailsService");
        auth.userDetailsService(securityUserDetailsService);
    }

}
