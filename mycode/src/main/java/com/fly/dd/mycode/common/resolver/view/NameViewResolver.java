package com.fly.dd.mycode.common.resolver.view;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;
import java.util.Map;

/**
 * Created by zhuyd on 2017/3/3.
 */
public class NameViewResolver implements ViewResolver{

    private Map<String,ViewResolver> resolvers;



    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {


        return null;
    }

    public Map<String, ViewResolver> getResolvers() {
        return resolvers;
    }

    public void setResolvers(Map<String, ViewResolver> resolvers) {
        this.resolvers = resolvers;
    }
}
