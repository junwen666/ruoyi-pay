package com.ruoyi.common.okhttp;

import com.google.common.collect.Maps;
import com.ruoyi.common.exception.PayException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class OkHttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(OkHttpUtils.class);

    private static final String HEADER_AUTH_KEY = "Authorization";

    private static OkHttpClient okHttpClient;

    static {
        okHttpClient = SpringUtils.getBean(OkHttpClient.class);
    }

    @Autowired
    public static void setOkHttpClient(OkHttpClient okHttpClient) {
        OkHttpUtils.okHttpClient = okHttpClient;
    }

    public static <T> T buildRequest(HttpDto httpDto, Function<Response, T> responseProcessor) {
        Request.Builder requestBuilder = new Request.Builder().url(httpDto.getUrl());

        if (StringUtils.isNotBlank(httpDto.getData())) {
            RequestBody requestBody = RequestBody.create(httpDto.getMediaType(), httpDto.getData());

            if (HttpMethod.POST.equals(httpDto.getHttpMethod())) {
                requestBuilder.post(requestBody);
            } else if (HttpMethod.PUT.equals(httpDto.getHttpMethod())) {
                requestBuilder.put(requestBody);
            } else if (HttpMethod.DELETE.equals(httpDto.getHttpMethod())) {
                requestBuilder.delete(requestBody);
            }
        }

        Map<String, String> headers = httpDto.getHeaders();
        if (StringUtils.isNotBlank(httpDto.getUsername()) || StringUtils.isNotBlank(httpDto.getPassword())) {
            if (headers == null) {
                headers = new HashMap<>(1);
            }
            String username = StringUtils.defaultIfBlank(httpDto.getUsername(), StringUtils.EMPTY);
            String password = StringUtils.defaultIfBlank(httpDto.getPassword(), StringUtils.EMPTY);
            headers.put(HEADER_AUTH_KEY, Credentials.basic(username, password));
        }
        if(MapUtils.isNotEmpty(headers)){
            requestBuilder.headers(Headers.of(headers));
        }
        Request request = requestBuilder.build();

        return execute(request,responseProcessor);
    }

    /**
     * 调用okhttp的newCall方法
     *
     * @param request
     * @return
     */
    private static <T> T execute(Request request, Function<Response, T> responseProcessor) {
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            return responseProcessor.apply(response);
        } catch (IOException e) {
            logger.error("okhttp execute error:{}", ExceptionUtils.getStackTrace(e));
            throw new PayException("渠道调用异常!");
        } finally {
            if (Objects.nonNull(response)) {
                response.close();
            }
        }
    }

}
