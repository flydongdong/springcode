package com.fly.dd.mycode.common.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登陆成功后的处理Handler(继承SimpleUrlAuthenticationSuccessHandler)
 * Created by zhuyd on 2017/3/29.
 */
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SecurityUser user = (SecurityUser) authentication.getPrincipal();

        setDefaultTargetUrl("/index");
        if(null != user){
//            Cookie cookie = new Cookie("loginUserName",user.getUsername());
//            cookie.setMaxAge(3600 * 24 );
//            response.addCookie(cookie);
            request.getSession().setAttribute("loginUserName",user.getUsername());
            request.getSession().setAttribute("loginRealname",user.getRealname());
        }
        super.handle(request, response, authentication);
    }
}
