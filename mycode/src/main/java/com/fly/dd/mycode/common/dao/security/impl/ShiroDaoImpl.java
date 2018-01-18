package com.fly.dd.mycode.common.dao.security.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fly.dd.mycode.common.dao.security.ShiroDao;
import com.fly.dd.mycode.common.entity.security.Permission;
import com.fly.dd.mycode.common.entity.security.Role;
import com.fly.dd.mycode.common.entity.security.User;
import com.fly.dd.mycode.common.entity.security.mapper.PermissionRowMapper;
import com.fly.dd.mycode.common.entity.security.mapper.RoleRowMapper;
import com.fly.dd.mycode.common.entity.security.mapper.UserRowMapper;

/**
 *
 * 如果使用shiro框架则用户信息都用这个
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年6月30日 下午3:19:56 $
 */
public class ShiroDaoImpl extends JdbcTemplate implements ShiroDao {

    @Override
    public User getUserByLoginName(String loginName) {
        String sql = "SELECT * FROM SYS_USERS WHERE USERNAME = ?";
        List<User> users = query(sql, new Object[] { loginName }, new UserRowMapper().instance());
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public List<Role> getUserRoles(Long id) {
        String sql = "SELECT A.* FROM SYS_ROLES A,SYS_USERS_ROLES B WHERE A.ID = B.ROLE_ID AND  B.USER_ID = ?";
        return query(sql, new Object[] { id }, new RoleRowMapper().instance());
    }

    @Override
    public List<Permission> getUserPermissionByRoleIds(Long[] ids) {
        if (ids == null || ids.length < 1) {
            return null;
        }
        String sql = "SELECT A.* FROM SYS_PERMISSIONS A,SYS_ROLES_PERMISSIONS B WHERE A.ID=B.PERMISSION_ID AND B.ROLE_ID IN ("
                + StringUtils.join(ids, ",") + ") GROUP BY B.PERMISSION_ID";
        return query(sql, new PermissionRowMapper().instance());
    }

    @Override
    public User getUserById(Long id) {
        String sql = "SELECT * FROM SYS_USERS WHERE ID = ?";
        return queryForObject(sql, new Object[] { id }, new UserRowMapper().instance());
    }

}
