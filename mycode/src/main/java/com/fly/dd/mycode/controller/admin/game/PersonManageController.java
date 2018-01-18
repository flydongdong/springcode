package com.fly.dd.mycode.controller.admin.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fly.dd.mycode.common.utils.Pagenation;
import com.fly.dd.mycode.entity.game.BasePerson;
import com.fly.dd.mycode.service.game.BasePersonService;
import com.fly.dd.mycode.utils.properties.NameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by zhuyd on 2017/3/31.
 */
@Controller
@RequestMapping("/admin/game/person/")
public class PersonManageController {

    @Autowired
    private BasePersonService basePersonService;

    /**
     * 进入页面
     * @return
     */
    @RequestMapping("personManage")
    public String personManage(){
        return "admin/game/personManage";
    }

    @RequestMapping(value = "queryBasePersonJson")
    @ResponseBody
    public String getBasePersonJson(@RequestParam(defaultValue = "2",required = false,name = "page") Integer page,
                                    @RequestParam(name = "rows",required = false,defaultValue = "5") Integer rows,
                                    @RequestParam(name = "sort",required = false) String sort,
                                    @RequestParam(name = "order",required = false) String order,
                                    HttpServletRequest request) throws JsonProcessingException {
        Map<String,String[]> paramsMap = request.getParameterMap();
        Set<String> keys = paramsMap.keySet();
        for (String key : keys) {
            String msg = "参数名"+key +"-->";
            String[] arr = paramsMap.get(key);
            System.out.println(msg + ArrayUtils.toString(arr));
        }
        Pagenation p = new Pagenation();
        p.setPageNum(page);
        p.setPageSize(rows);
        List<BasePerson> list = new ArrayList<>();
        list = basePersonService.findBasePersonListWithPage(p,order,sort);
        Map<String,Object> jsonData = new HashMap<>();
        jsonData.put("total",p.getRowTotalNum());
        jsonData.put("rows",list);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(jsonData);
        return json;
    }

    /**
     * 初始化人物数据
     */
    @RequestMapping("initPersonData/{num}")
    @ResponseBody
    public String initPersonData(@PathVariable("num") int num){
        StringBuilder msg = new StringBuilder("");
        if( num>0 ){
            for(int i= 0; i < num ; i++){
                BasePerson obj = new BasePerson();
                String name = NameUtils.generateName((int)(Math.random())*2);
                obj.setName(name);
                obj.setAge((int)(Math.random()*35) + 10);
                obj.setExp(0l);
                obj.setMaxHP((int)(Math.random()*20) + 90);
                obj.setMaxMP((int)(Math.random()*20) + 90);
                obj.setMaxPower((int)(Math.random()*25) + 5);
                obj.setMaxIt((int)(Math.random()*25) + 5);
                basePersonService.add(obj);
                msg.append(","+name);
            }
        }else{
            return "初始化数据失败！";
        }
        return "成功初始化数据成功！<br/>人物名为【"+msg.substring(1)+"】！";
    }

    /**
     * 编辑窗口内容
     * @return
     */
    @RequestMapping("editBasePerson")
    public String editBasePerson(@RequestParam(name = "id",required = false,defaultValue = "-1") long id, Model map){

        if(id > 0 ){
            map.addAttribute("isEdit",true);
        }
        return "admin/game/editBasePerson";
    }

}
