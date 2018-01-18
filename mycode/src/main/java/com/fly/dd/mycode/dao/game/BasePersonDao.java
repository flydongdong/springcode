package com.fly.dd.mycode.dao.game;

import com.fly.dd.mycode.common.utils.Pagenation;
import com.fly.dd.mycode.entity.game.BasePerson;

import java.util.List;

/**
 * Created by zhuyd on 2017/4/7.
 */
public interface BasePersonDao {

    /**
     * 分页获取列表信息
     * @param page
     * @param order
     * @param sort
     * @return
     */
    public List<BasePerson> findListWithPage(Pagenation page, String order, String sort);

    /**
     * 新增
     * @param obj
     * @return
     */
    public int add(BasePerson obj);
}
