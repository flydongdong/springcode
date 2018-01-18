package com.fly.dd.mycode.common.dao.base.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.BatchSqlUpdate;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * jdbcTmeplate封装
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年5月9日 下午3:53:31 $
 */
public class BaseJdbcDaoImpl2<T> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    /**
     * 查询单表的记录数
     *
     * @param tableName
     *            表名
     * @return
     */
    protected int querySingleTableRowCount(String tableName) {
        if (StringUtils.isBlank(tableName)) {
            return 0;
        }
        String sql = " SELECT COUNT(1) FROM " + tableName;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * 根据条件查询单表的记录数<br>
     * 注意：参数与值必须对应
     *
     * @param tableName
     *            表名
     * @param conditions
     *            列参数名
     * @param conditionsParams
     *            列参数对应的值
     * @return
     */
    protected int querySingleTableRowCount(String tableName, String[] conditions, Object[] conditionsParams) {
        if (StringUtils.isBlank(tableName)) {
            return -1;
        }
        StringBuilder sql = new StringBuilder(" SELECT COUNT(1) FROM " + tableName + " WHERE 1 = 1 ");
        if (conditions != null && conditionsParams != null) {
            if (conditions.length != conditionsParams.length) {
                return -2;
            }
            for (int i = 0; i < conditions.length; i++) {
                sql.append(" AND " + conditions[i] + " = ? ");
            }
        }
        return jdbcTemplate.queryForObject(sql.toString(), Integer.class, conditionsParams);
    }

    /**
     * 查询一条记录 利用RowMapper<T>映射
     *
     * @param t
     *            (rowMapper为空时不能为空)
     * @param rowMapper
     *            (如果为空,就默认使用BeanPropertyRowMapper映射,此时列表名要与实体类名形成规则)
     * @param sql
     *            带占位符
     * @param args
     *            对应参数
     * @return
     */
    protected T queryForSingleRow(String sql, Class<T> t, RowMapper<T> rowMapper, Object... args) {
        if (rowMapper == null) {
            rowMapper = new BeanPropertyRowMapper<T>(t);
        }
        T calss = jdbcTemplate.queryForObject(sql, rowMapper, args);
        return calss;
    }

    /**
     * 参数设置类型的更新
     *
     * @param sql
     * @param args
     *            对应占位符的参数值
     * @param argTypes
     *            对应占位符的参数列的类型
     * @return
     */
    protected int update(String sql, Object[] args, int[] argTypes) {
        return jdbcTemplate.update(sql, args, argTypes);
    }

    /**
     * 参数设置类型的更新
     *
     * @param sql
     * @param args
     *            对应占位符的参数值
     * @param argTypes
     *            对应占位符的参数列的类型
     * @return
     */
    protected int execute(String sql, Object[] args, int[] argTypes) {
        return update(sql, args, argTypes);
    }

    /**
     * 更新
     *
     * @param sql
     * @param args
     *            对应占位符的参数值
     * @return
     */
    protected int update(String sql, Object[] args) {
        return jdbcTemplate.update(sql, args);
    }

    /**
     * 更新
     *
     * @param sql
     * @param args
     *            对应占位符的参数值
     * @return
     */
    protected int execute(String sql, Object[] args) {
        return update(sql, args);
    }

    /**
     * 根据id 批量处理数据或者只处理一个数据(一般用于删除)
     *
     * @param sql
     * @param ids
     * @return
     */
    protected boolean batchUpdate(String sql, final String[] ids) {
        if (ids.length == 1) {
            jdbcTemplate.update(sql, ids[0]);
            return true;
        }
        else if (ids.length > 1 && ids.length < 20000) {// 小于2W条记录时
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, ids[i]);
                }

                @Override
                public int getBatchSize() {
                    return ids.length;
                }
            });
            return true;
        }
        else if (ids.length >= 20000) {
            BatchSqlUpdate batchSqlUpdate = new BatchSqlUpdate(dataSource, sql);
            batchSqlUpdate.setBatchSize(5);// 设置每次更新量:分批处理
            batchSqlUpdate.setTypes(new int[] { Types.CHAR });// 设定占位符?类型
            for (int i = 0; i < ids.length; i++) {
                batchSqlUpdate.update(new Object[] { ids[i] });
            }
            return true;
        }
        return false;
    }

    /**
     * 根据RowMapper来映射查询结果
     *
     * @param sql
     * @param rowMapper
     * @return
     */
    protected T queryForObject(String sql, RowMapper<T> rowMapper) {
        return jdbcTemplate.queryForObject(sql, rowMapper);
    }

    /**
     * 根据RowMapper来映射查询结果
     *
     * @param sql
     * @param args
     *            占位符对应的值
     * @param rowMapper
     * @return
     */
    protected T queryForObject(String sql, Object[] args, RowMapper<T> rowMapper) {
        return jdbcTemplate.queryForObject(sql, args, rowMapper);
    }

    /**
     * 指定查询返回的单个列的值的类型
     *
     * @param sql
     * @param t
     * @return
     */
    protected T queryForObject(String sql, Class<T> t) {
        return jdbcTemplate.queryForObject(sql, t);
    }

    /**
     * 指定查询返回的单个列的值的类型
     *
     * @param sql
     * @param t
     * @param args
     * @return
     */
    protected T queryForObject(String sql, Class<T> t, Object[] args) {
        return jdbcTemplate.queryForObject(sql, t, args);
    }

    /**
     * 查找List集合
     *
     * @param sql
     * @param rowMapper
     * @param args
     * @return
     */
    protected List<T> query(String sql, RowMapper<T> rowMapper, Object[] args) {
        return jdbcTemplate.query(sql, rowMapper, args);
    }

    /**
     * 查找List集合
     *
     * @param sql
     * @param rowMapper
     * @return
     */
    protected List<T> query(String sql, RowMapper<T> rowMapper) {
        return jdbcTemplate.query(sql, rowMapper);
    }

    /**
     * 获取jdbcTemplate
     *
     * @return
     */
    protected JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

}
