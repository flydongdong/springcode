package com.fly.dd.mycode.common.dao.security;

import java.util.List;

import com.fly.dd.mycode.common.entity.security.Permission;
import com.fly.dd.mycode.common.entity.security.Role;
import com.fly.dd.mycode.common.entity.security.User;

public interface ShiroDao {

    /**
     * 根据登录名获取用户
     *
     * @param loginName
     * @return
     */
    public User getUserByLoginName(String loginName);

    /**
     * 获取用户的角色信息
     *
     * @param id
     * @return
     */
    public List<Role> getUserRoles(Long id);

    /**
     * 根据角色id获取权限信息
     *
     * @param ids
     * @return
     */
    public List<Permission> getUserPermissionByRoleIds(Long[] ids);

    /**
     * 根据用户id获取信息
     *
     * @param id
     * @return
     */
    public User getUserById(Long id);
}
