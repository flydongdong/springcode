package com.fly.dd.mycode.common.entity.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fly.dd.mycode.common.entity.security.Role;

/**
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年6月30日 下午3:55:33 $
 */
public class RoleRowMapper implements RowMapper<Role> {

    private static RoleRowMapper rowMapper = new RoleRowMapper();

    public RoleRowMapper instance() {
        return rowMapper;
    }

    public Role mapRow(ResultSet rs, int arg1) throws SQLException {
        Role role = new Role();
        role.setId(rs.getLong("ID"));
        role.setRole(rs.getString("ROLE"));
        role.setDescription(rs.getString("DESCRIPTION"));
        role.setAvailable(rs.getBoolean("available"));
        return role;
    }

}
