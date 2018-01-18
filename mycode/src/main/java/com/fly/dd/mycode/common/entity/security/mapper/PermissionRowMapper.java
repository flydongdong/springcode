package com.fly.dd.mycode.common.entity.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fly.dd.mycode.common.entity.security.Permission;

/**
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年6月30日 下午5:13:22 $
 */
public class PermissionRowMapper implements RowMapper<Permission> {

    private static PermissionRowMapper rowMapper = new PermissionRowMapper();

    public PermissionRowMapper instance() {
        return rowMapper;
    }

    public Permission mapRow(ResultSet rs, int arg1) throws SQLException {
        Permission permission = new Permission();
        permission.setId(rs.getLong("ID"));
        permission.setPermission(rs.getString("PERMISSION"));
        permission.setDescription(rs.getString("DESCRIPTION"));
        permission.setAvailable(rs.getBoolean("AVAILABLE"));
        return permission;
    }

}
