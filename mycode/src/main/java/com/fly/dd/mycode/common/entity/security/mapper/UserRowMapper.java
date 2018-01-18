package com.fly.dd.mycode.common.entity.security.mapper;

import com.fly.dd.mycode.common.entity.security.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年6月30日 下午3:28:17 $
 */
public class UserRowMapper implements RowMapper<User> {

    private static UserRowMapper rowMapper = new UserRowMapper();

    public UserRowMapper instance() {
        return rowMapper;
    }

    public User mapRow(ResultSet rs, int arg1) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("ID"));
        user.setUsername(rs.getString("USERNAME"));
        user.setPassword(rs.getString("PASSWORD"));
        user.setRealname(rs.getString("REALNAME"));
        user.setSalt(rs.getString("SALT"));
        user.setLocked(rs.getBoolean("LOCKED"));
        return user;
    }

}
