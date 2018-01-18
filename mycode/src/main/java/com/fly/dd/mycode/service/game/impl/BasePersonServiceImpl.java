package com.fly.dd.mycode.service.game.impl;

import com.fly.dd.mycode.common.utils.Pagenation;
import com.fly.dd.mycode.dao.game.BasePersonDao;
import com.fly.dd.mycode.entity.game.BasePerson;
import com.fly.dd.mycode.service.game.BasePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhuyd on 2017/4/17.
 */
@Service("basePersonService")
public class BasePersonServiceImpl implements BasePersonService{

    @Autowired
    private BasePersonDao basePersonDao;

//    @Cacheable(value = "basePersonJson",key = "#order+'_'+#sort")
    @Override
    public List<BasePerson> findBasePersonListWithPage(Pagenation page,String order,String sort) {
        return basePersonDao.findListWithPage(page,order,sort);
    }

    @Override
    public int add(BasePerson obj) {
        return basePersonDao.add(obj);
    }


}
