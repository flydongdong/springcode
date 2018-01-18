package com.fly.dd.mycode.common.resolver;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 用来绑定Contrller参数的，
 * </p>
 * 如果加上此参数，表示采用MyMethodArgumentsResolver类解析参数值
 *
 * @author zhuyd
 * @version $Revision: 1.0 $, $Date: 2016年5月4日 下午2:44:54 $
 */
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FormModel {

    String value();
}
