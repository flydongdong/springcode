package com.fly.dd.mycode.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年11月24日 下午4:07:01 $
 */
@Controller
public class IndexController {





    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
