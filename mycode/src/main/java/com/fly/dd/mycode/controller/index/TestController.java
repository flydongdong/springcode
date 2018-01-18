package com.fly.dd.mycode.controller.index;

import com.fly.dd.mycode.common.entity.security.User;
import com.fly.dd.mycode.common.view.ExcelView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 测试
 */
@RequestMapping("/test")
@Controller
public class TestController {

//    @Autowired
//    private AmqQueueSender amqQueueSender;

//    @ResponseBody
//    @RequestMapping("/queneSender/{msg}")
//    public String testQueneSender(@PathVariable("msg") String msg) {
//        try {
//            System.out.println("->测试amqQueueSender发送消息：" + msg);
//            amqQueueSender.send("test.msg", msg);
//        }
//        catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//        String rs = "AmqQueneSender发送了一条信息：->" + msg;
//        return rs;
//    }

    @RequestMapping("/excel")
    public ModelAndView testExcelExport(Map<String,Object> model){
        User a = new User();
        a.setId(11l);
        a.setUsername("zhuyd1");

        User b = new User();
        b.setId(12l);
        b.setUsername("zhuyd2");

        User c = new User();
        c.setId(13l);
        c.setUsername("zhuyd3");
        List<User> list = new ArrayList<User>();
        list.add(a);
        list.add(b);
        list.add(c);
        model.put("list",list);
        String[] properties = new String[]{"id","username"};
        model.put("properties",properties);
        return new ModelAndView(new ExcelView());
    }

    @RequestMapping("/test1")
    @ResponseBody
    public  String test1(){

        return "哈哈你一脸";
    }

}
