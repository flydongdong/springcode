package com.fly.dd.mycode.controller.index;

import com.fly.dd.mycode.common.security.SecurityGrantedAuthority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

@Controller
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/login")
    public String login() {

//        log.info(ContextLoader.getCurrentWebApplicationContext().getBean("messageSourceHelper", MessageSourceHelper.class).getMessageByCode("jdbc.database"));
        // log.debug("有用户名登录");
        return "login";
    }

    // 此url测试与security默认表单验证，事实证明此url无效
    @RequestMapping("/login_check")
    public String login_check(@RequestParam String username, @RequestParam String password,
            @RequestParam String remember_me) {
        log.debug("登录:-->" + username + "=====" + password);
        return "admin";
    }

    // 登录成功后跳转的url
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping("/unamenotfind")
    public String unamenotfind(Map<String, Object> map, HttpServletRequest request) {
        System.out.println(request.getParameter("username"));
        map.put("error", "用户名或者密码错误！");
        map.put("username", request.getParameter("username"));
        return "login";
    }

    /**
     * 测试权限
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/testAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String testAuth(SecurityContextHolderAwareRequestWrapper request) {
        // 判断是否有此权限
        if (request.isUserInRole("system:index:*")) {// 非注解判断是否有权限
            System.out.println("有权限-->system:index:*");
        }
        Collection<SecurityGrantedAuthority> a = (Collection<SecurityGrantedAuthority>) SecurityContextHolder
                .getContext().getAuthentication().getAuthorities();
        System.out.println(a.contains(new SecurityGrantedAuthority("system:*")));
        Iterator<? extends GrantedAuthority> its = a.iterator();
        while (its.hasNext()) {
            System.out.println(its.next().getAuthority());
        }
        return "good";
    }

    /**
     * 测试无权限跳转
     *
     * @return
     */
    @RequestMapping("/no_auth")
    public String no_auth(Map<String, Object> map) {
        map.put("msg", "恭喜你，没有权限！");
        return "no_auth";
    }
}
