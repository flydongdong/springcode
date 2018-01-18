package com.fly.dd.mycode.common.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * 权限
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年12月8日 下午3:56:40 $
 */
public class SecurityGrantedAuthority implements GrantedAuthority {

    private String permission;

    private static final long serialVersionUID = 1L;

    /**
     *
     * @param permission
     *            直接权限
     */
    public SecurityGrantedAuthority(String permission) {
        this.permission = permission;
    }

    /**
     * 获取权限值(permission)
     */
    public String getAuthority() {
        return permission;
    }

    /**
     * 重写返回权限值(permission)
     */
    @Override
    public String toString() {
        return this.permission;
    }

    /**
     * 重写equals 便于set过滤
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof SecurityGrantedAuthority) {
            return permission.equals(((SecurityGrantedAuthority) obj).permission);
        }

        return false;
    }

    /**
     * 重写hashcode
     */
    @Override
    public int hashCode() {
        return this.permission.hashCode();
    }
}
