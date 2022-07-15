package com.ruoyi.common.utils.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.FIELD })
public @interface FormKey {

    String value() default "";

    int maxLength() default -1;

    /** 是否忽略的字段 **/
    boolean ignore() default false;

}
