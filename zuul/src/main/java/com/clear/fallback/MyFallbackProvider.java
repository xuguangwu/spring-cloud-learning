package com.clear.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Component
public class MyFallbackProvider implements FallbackProvider {

    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() { //返回一个HttpStatus对象 这个对象是个枚举对象， 里面包含了一个status code 和 reasonPhrase信息
                return status;
            }

            //返回status的code 比如 404，500等
            @Override
            public int getRawStatusCode() {
                return status.value();
            }

            //返回一个HttpStatus对象的reasonPhrase信息
            @Override
            public String getStatusText() {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            //返回降级信息
            @Override
            public InputStream getBody() {
                return new ByteArrayInputStream("降级信息".getBytes());
            }

            //需要对响应报头设置的话可以在此设置
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
