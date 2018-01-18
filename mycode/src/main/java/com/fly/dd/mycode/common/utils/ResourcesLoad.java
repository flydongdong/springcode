package com.fly.dd.mycode.common.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * 利用PathMatchingResourcePatternResolver加载资源文件
 * Created by zhuyd on 2017/4/18.
 */
public class ResourcesLoad{
    private static final Logger log = LoggerFactory.getLogger(ResourcesLoad.class);

    private static final String PATH = "classpath:properties/**/*.properties";
    //匹配路径的资源解析器
    private static  final ResourcePatternResolver RESOLVER = new PathMatchingResourcePatternResolver();
    //存放的properties资源
    private static Map<String,String> properties = new ConcurrentHashMap<>();

    static {
        ResourcesLoad.init();
    }


    /**
     * 初始化
     */
    private static void init(){
        loadProperties(loadResources());
    }


    /**
     * 获取Rescource
     * @return
     */
    private static Resource[] loadResources(){
        Resource[] resources = null;
        try {
             resources = RESOLVER.getResources(PATH);

        } catch (IOException e) {
            log.error("ResourcesLoad加载资源文件失败，原因:"+e.getMessage());
//            e.printStackTrace();
        }
        return resources;
    }

    private static Map<String,String> loadProperties(Resource[] resources){
        if (ArrayUtils.isEmpty(resources)) {
            return properties;
        }
        for (Resource resource:resources) {
            try {
                InputStream in = resource.getInputStream();
                Properties p = new Properties();
                p.load(new InputStreamReader(in,"UTF-8"));
                Enumeration<Object> it = p.keys();
                while (it.hasMoreElements()) {
                    String key = (String) it.nextElement();
                    String t = (String) p.get(key);
                    if (properties.containsKey(key)) {
                        log.warn("ResourcesLoad加载资源文件key"+ key +" 值重复！");
                    }
                    else {
                        properties.put(key.trim(), t);
                    }
                }
            } catch (IOException e) {
                log.error("ResourcesLoad加载资源文件失败，原因:"+e.getMessage());
//                e.printStackTrace();
            }
        }


        return properties;
    }

    /**
     * 根据key获取资源文件
     * @param key
     * @return
     */
    public static String  getProperty(String key){
        String msg = properties.get(key);
        return msg != null ? msg.trim() : msg;
    }


}
