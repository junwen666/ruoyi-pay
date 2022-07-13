package com.ruoyi.common.okhttp;

import lombok.Getter;
import lombok.Setter;
import okhttp3.MediaType;
import org.springframework.http.HttpMethod;

import java.util.Map;

@Getter
@Setter
public class HttpDto {

    private String url;

    private HttpMethod httpMethod;

    private MediaType mediaType;

    private Map<String, String> headers;

    private String data;

    private String username;

    private String password;

}
