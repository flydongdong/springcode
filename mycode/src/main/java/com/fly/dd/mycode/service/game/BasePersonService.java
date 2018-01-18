package com.fly.dd.mycode.service.game;

import com.fly.dd.mycode.common.utils.Pagenation;
import com.fly.dd.mycode.entity.game.BasePerson;

import java.util.List;

/**
 * Created by zhuyd on 2017/3/31.
 */
public interface BasePersonService {

    /**
     * 分页获取列表信息
     * @param page
     * @param order
     * @param sort
     * @return
     */
    public List<BasePerson> findBasePersonListWithPage (Pagenation page,String order,String sort);

    /**
     * 新增基本人物
     * @param obj
     * @return
     */
    public int add(BasePerson obj);
}
