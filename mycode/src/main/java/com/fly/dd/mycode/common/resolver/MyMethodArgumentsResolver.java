package com.fly.dd.mycode.common.resolver;

import java.lang.reflect.Field;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 自定义Controller方法参数解析器 <br>
 * 表单与类属性值解析<br>
 * 规则：在参数前加上@FormModel如果注解有value属性且不为空的情况下前缀采用此值+"."，否则采用参数的名字作为前缀<br>
 * 注意：实现类是org.springframework.web.method.support.HandlerMethodArgumentResolver(support里的不是messgage里的)
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年5月4日 下午1:40:40 $
 */
public class MyMethodArgumentsResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 表示有FormModel注解的参数
        return parameter.hasParameterAnnotation(FormModel.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String objName = "";
        // 获取注解上的value值
        String value = parameter.getParameterAnnotation(FormModel.class).value();
        // 如有多个需要处理的注解(就要获取如下的注解再进行处理，未处理。。。)
        // Annotation[] ats =parameter.getMethodAnnotations();
        if (StringUtils.isNoneBlank(value)) {// 如果注解存在value值则参数名就采用value+"."前缀
            objName = value + ".";
        }
        else {
            objName = parameter.getParameterName() + ".";
        }
        // 通过参数类型初始化对象
        Object o = BeanUtils.instantiate(parameter.getParameterType());

        // 获取对应类型的属性
        Field[] frr = parameter.getParameterType().getDeclaredFields();

        // StringBuilder sb;// 将参数名字符串转化
        String sb = "";
        String[] val;// 获取某个参数的值(可能为多数)
        // 获取所有请求参数名字
        Iterator<String> params = webRequest.getParameterNames();
        while (params.hasNext()) {
            if (!sb.startsWith(objName)) {
                continue;
            }
            // sb = new StringBuilder(params.next());// 参数名
            // if (sb.indexOf(".") < 0) {//
            // continue;
            // }

            for (int i = 0; i < frr.length; i++) {
                // 设置可以访问的。。。
                frr[i].setAccessible(true);
                if (sb.toString().equals(objName + frr[i].getName())) {
                    // 获取对应参数名的参数值
                    val = webRequest.getParameterValues(sb.toString());
                    webRequest.getParameterValues(sb.toString());
                    frr[i].set(o, val[0]);// 给属性赋值（值取一个值）
                }
            }
        }
        return o;
    }

}
