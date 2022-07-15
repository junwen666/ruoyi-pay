package com.ruoyi.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FastByteArrayOutputStream;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Request工具类
 *
 * <p>All rights Reserved, Designed By VEVOR</p>
 *
 * @Copyright: Copyright(C) 2016
 * @Company: VEVOR.
 * @author: zhongliping
 * @Createdate: 2017年1月12日下午5:01:05
 */
public class RequestUtils {

    private static final Logger logger = LoggerFactory.getLogger(RequestUtils.class);

    private static final int BUFFER_SIZE = 1024;

    /**
     * 获取request GET参数
     *
     * @param request
     * @return kv
     */
    public static Map<String, String> getRequestParams(HttpServletRequest request) {
        Map<String, String[]> paramsMap = request.getParameterMap();
        Map<String, String> reqMap = new HashMap<String, String>(paramsMap.size());
        paramsMap.forEach((key, values) -> reqMap.put(key, values[0]));
        return reqMap;
    }

    /**
     * 获取request POST内容
     *
     * @param request
     * @return
     */
    public static String getRequestContent(HttpServletRequest request) {
        String content = null;
        try {
            byte[] data = getRequestBytes(request);
            if (data != null) {
                String encode = request.getCharacterEncoding();
                content = new String(data, encode);
            }
        } catch (Exception e) {
            logger.error("RequestUtils.getRequestContent error", e);
        }
        return content;
    }

    /**
     * 获取request内容
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static byte[] getRequestBytes(HttpServletRequest request) throws IOException {
        try (InputStream is = request.getInputStream();
             FastByteArrayOutputStream baos = new FastByteArrayOutputStream()) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int length;
            while ((length = is.read(buffer)) > 0) {
                baos.write(buffer, 0, length);
            }
            return baos.toByteArray();
        }
    }


}
