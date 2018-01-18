package com.fly.dd.mycode.common.view;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 自定义Freemarker视图
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年4月6日 下午2:36:55 $
 */
public class MyFreemarkerView extends FreeMarkerView {

    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        model.put("base", request.getContextPath());
//        model.put("cookies",request.getc);
        super.exposeHelpers(model, request);
    }

//    private Map<String,Object> getCookiesForMap(Cookie[] cookies){
//        Map<String,Object>  map = new HashMap<>();
//
//    }

}
