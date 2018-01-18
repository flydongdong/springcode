package com.fly.dd.mycode.common.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 继承的类实现了UserDetails
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年12月5日 下午3:46:31 $
 */
public class SecurityUser extends User {

    private static final long serialVersionUID = -276003395746371294L;

    private String realname;

    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SecurityUser(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(password, password, accountNonLocked, accountNonLocked, accountNonLocked, accountNonLocked, authorities);
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
