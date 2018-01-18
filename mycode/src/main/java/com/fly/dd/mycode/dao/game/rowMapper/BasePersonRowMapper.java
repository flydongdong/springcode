package com.fly.dd.mycode.dao.game.rowMapper;

import com.fly.dd.mycode.entity.game.BasePerson;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhuyd on 2017/4/7.
 */
public class BasePersonRowMapper implements RowMapper<BasePerson>{

    public static final BasePersonRowMapper rowMapper = new  BasePersonRowMapper();

    @Override
    public BasePerson mapRow(ResultSet rs, int i) throws SQLException {
        BasePerson person = new BasePerson();
        person.setId(rs.getLong("ID"));
        person.setName(rs.getString("NAME"));
        person.setAge(rs.getInt("AGE"));
        person.setMaxPower(rs.getInt("MAX_POWER"));
        person.setMaxIt(rs.getInt("MAX_IT"));
        person.setMaxHP(rs.getInt("MAX_HP"));
        person.setMaxMP(rs.getInt("MAX_MP"));
        return person;
    }
}
