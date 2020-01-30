package com.hyp.learn.zuul.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author hyp
 * Project name is spring-cloud-learn
 * Include in com.hyp.learn.zuul.fallback
 * hyp create at 20-1-30
 **/
@Component
public class UserFallbackProvider implements FallbackProvider {

    @Override
    public String getRoute() {
        // 表明是为哪个微服务提供回退
        return "user-service";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                // fallback时的状态码
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                // 数字类型的状态码
                return getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                // 状态文本信息
                return getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                // 响应体
                return new ByteArrayInputStream("User Service is not avaiable, please try again later."
                        .getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                // headers设定
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json",
                        Charset.forName("UTF-8"));
                headers.setContentType(mt);

                return headers;
            }
        };
    }


}
