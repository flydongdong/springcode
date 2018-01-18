package com.fly.dd.mycode.utils.properties;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

/**
 *
 * (代码验证未能成功故暂时不适用)结合使用EmbeddedValueResolverAware来加载spring初始化properties资源文件数据
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年11月24日 下午3:20:24 $
 */
// 放到配置中初始化
// @Component("springPropertiesUtils")
public class PropertiesUtils implements EmbeddedValueResolverAware {

    private static StringValueResolver stringValueResolver;

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        stringValueResolver = resolver;
    }

    /**
     * 获取资源文件中的值
     *
     * @param key
     * @return
     */
    public static String getPropertiesValue(String key) {
        return stringValueResolver.resolveStringValue(key);
    }

}
