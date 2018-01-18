package com.fly.dd.mycode.dao.game.impl;

import com.fly.dd.mycode.common.dao.base.impl.BaseJdbcDaoImpl;
import com.fly.dd.mycode.common.utils.Pagenation;
import com.fly.dd.mycode.dao.game.BasePersonDao;
import com.fly.dd.mycode.dao.game.rowMapper.BasePersonRowMapper;
import com.fly.dd.mycode.entity.game.BasePerson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhuyd on 2017/4/7.
 */
@Repository("basePersonDao")
public class BasePersonDaoImpl extends BaseJdbcDaoImpl implements BasePersonDao  {


    @Override
    public List<BasePerson> findListWithPage(Pagenation page, String order, String sort) {
        System.out.println("数据库存储");
        StringBuilder  sql = new StringBuilder(" SELECT * FROM T_BASE_PERSON ");
        if(StringUtils.isNotBlank(order) && StringUtils.isNotBlank(sort)){
            sql.append(" ORDER BY " + sort +" " + order);
        }
//        System.out.println(sql.toString());
        if(page == null){
            return query(sql.toString(),null, BasePersonRowMapper.rowMapper);
        }
        return query(sql.toString(),null,BasePersonRowMapper.rowMapper,page);
    }



    @Override
    public int add(BasePerson obj) {
        String sql = "INSERT INTO T_BASE_PERSON(NAME,AGE,max_power,max_it,max_HP,max_MP,EXP) VALUES(?,?,?,?,?,?,?)";
        return update(sql,new Object[]{
                obj.getName(),obj.getAge(),obj.getMaxPower(),obj.getMaxIt(),obj.getMaxHP(),obj.getMaxMP(),obj.getExp()
        });
    }
}
