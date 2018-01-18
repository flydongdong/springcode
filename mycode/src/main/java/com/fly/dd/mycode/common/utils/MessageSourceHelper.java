/*
 * @(#)MessageSourceHelper.java    Created on 2016年11月24日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * ${user}$
 */
package com.fly.dd.mycode.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

/**
 * 获取spring初始化的properties资源(默认是ResourceBundleMessageSource获取方式)
 * 注意：只能够在spring初始化之后使用
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年11月24日 下午7:14:58 $
 */
//@Component("messageSourceHelper")
//@Order(Integer.MIN_VALUE+100)
public class MessageSourceHelper extends  ResourceBundleMessageSource{

    private static final Logger log = LoggerFactory.getLogger(MessageSourceHelper.class);
    /**
     * 配置的properties文件的路径
     */
    private static final String[] beannames = new String[]{
            "properties/common"
    };

    public MessageSourceHelper() {
        log.info("初始化（"+ Integer.MIN_VALUE+100 +"）====》MessageSourceHelper");
        super.setBasenames(beannames);
    }

    /**
     * 根据code获取properties属性值
     * @param code
     * @return
     */
    public String getMessageByCode(String code){
        String msg = getMessage(code, null, null, Locale.CHINA);
        return msg != null ? msg.trim() : msg;
    }

//    private ResourceBundleMessageSource messageSource;
//    /**
//     * spring实例化的对象
//     */
//    private static MessageSourceHelper selfObject;
//
//    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
//        String msg = messageSource.getMessage(code, args, defaultMessage, locale);
//        return msg != null ? msg.trim() : msg;
//    }
//
//    /**
//     *
//     * 根据key获取
//     *
//     * @param code
//     *            属性key值
//     * @return 若无返回null
//     */
//    private String getMessage(String code) {
//        String msg = messageSource.getMessage(code, null, null, Locale.CHINA);
//        return msg != null ? msg.trim() : msg;
//    }
//
//    public void setMessageSource(ResourceBundleMessageSource messageSource) {
//        this.messageSource = messageSource;
//    }

//    /**
//     * 根据关键字获取(如果有多个则取最后一个)
//     *
//     * @param code
//     * @return
//     */
//    public static String getMessageByCode(String code) {
//        if (selfObject == null) {
//            log.info("初始化资源========>MessageSourceHelper,spring实例Bean的ID-->messageSourceHelper");
//            WebApplicationContext loader = ContextLoader.getCurrentWebApplicationContext();
//            System.out.println(loader);
//            if(loader == null){
//                return null;
//            }
//            selfObject = loader.getBean("messageSourceHelper",
//                    MessageSourceHelper.class);
//        }
//        if (selfObject == null) {
//            log.warn("MessageSourceHelper初始化资源MessageSourceHelper为空",
//                    new NullPointerException("messageSourceHelper值为空异常！"));
//        }
//        else {
//            return selfObject.getMessage(code);
//        }
//        return null;
//    }

}
