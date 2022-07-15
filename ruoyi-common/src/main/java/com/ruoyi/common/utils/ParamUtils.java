package com.ruoyi.common.utils;

import com.ruoyi.common.exception.PayException;
import com.ruoyi.common.utils.annotation.FormKey;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Objects;

public class ParamUtils {

    private static final Logger logger = LoggerFactory.getLogger(ParamUtils.class);

    public static String formString(Object data) {
        StringBuilder builder = new StringBuilder();
        if (Objects.isNull(data)) {
            return builder.toString();
        }

        try {
            Class<?> dataClass = data.getClass();
            Field[] declaredFields = dataClass.getDeclaredFields();
            if (ArrayUtils.isNotEmpty(declaredFields)) {

                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    String name = field.getName();
                    if (StringUtils.equals(name, "class")) {
                        continue;
                    }
                    Object value = field.get(data);
                    if (field.isAnnotationPresent(FormKey.class)) {
                        FormKey formKey = field.getAnnotation(FormKey.class);
                        if(formKey.ignore()){
                            continue;
                        }
                        name = formKey.value();
                    }
                    builder.append("&").append(name).append("=").append(value.toString());
                }

                // 删除开头 & 符号
                builder.delete(0, 1);
            }
        } catch (Exception e) {
            logger.error("build formString error,e", e);
            throw new PayException("build formString error");
        }

        return builder.toString();
    }

}
