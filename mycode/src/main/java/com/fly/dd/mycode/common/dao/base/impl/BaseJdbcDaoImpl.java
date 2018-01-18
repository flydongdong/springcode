package com.fly.dd.mycode.common.dao.base.impl;

import com.fly.dd.mycode.common.utils.Pagenation;
import com.fly.dd.mycode.common.utils.ResourcesLoad;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by zhuyd on 2017/4/6.
 */
public class BaseJdbcDaoImpl<T> {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    private static final Logger log = LoggerFactory.getLogger(BaseJdbcDaoImpl.class);

    private static String DATABASE_TYPE = null;// 数据库类型mysql,oracle,db2...

    static {
        if(DATABASE_TYPE == null){
            DATABASE_TYPE = ResourcesLoad.getProperty("jdbc.database");
            if(DATABASE_TYPE != null){
                log.info("BaseJdbcDaoImpl初始化数据库名-->" + DATABASE_TYPE);
            }else{
                log.warn("BaseJdbcDaoImpl初始化数据库名失败！！！！！！！-->");
            }
        }
    }

    public BaseJdbcDaoImpl() {

    }

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
     * 根据RowMapper查询结果
     * @param sql
     * @param args
     * @param rowMapper
     * @return
     */
    protected List<?> query(String sql,Object[] args ,RowMapper<T> rowMapper){
        return jdbcTemplate.query(sql,args,rowMapper);
    }

    /**
     * 分页获取列表信息
     * @param sql
     * @param args
     * @param rowMapper
     * @param page
     * @return
     */
    protected List<?> query(String sql, Object[] args, RowMapper<T> rowMapper, Pagenation page){
        if(page != null){
            if ("mysql".equalsIgnoreCase(DATABASE_TYPE)) {// mysql
                int countTotalNum;// 记录总数
                String countSql = " SELECT COUNT(1) FROM ( " + sql + " ) A ";
                countTotalNum = queryForInt(countSql,args);
                page.initalize(countTotalNum);
                sql = " SELECT A.* FROM ( " + sql + " ) A LIMIT " + page.getStartRow() + "," + page.getPageSize();
            }else{
                log.error("未找到分页目标数据库！");
                return null;
            }
        }

        return query(sql,args,rowMapper);
    }

    /**
     * 查询返回int结果
     *
     * @param sql
     * @param args
     * @return
     */
    protected  int queryForInt(String sql,Object[] args){
        return jdbcTemplate.queryForObject(sql,args,Integer.class);
    }

    /**
     *
     * @param sql
     * @param args
     * @param t
     * @return
     */
    protected  T queryForObject(String sql,Object[] args,Class<T> t){
        return jdbcTemplate.queryForObject(sql,args,t);
    }


    /**
     * 更新
     * @param sql
     * @param args
     * @return
     */
    protected  int update(String sql,Object[] args){
        return jdbcTemplate.update(sql,args);
    }

}
