package com.example.apiencryption.config;

import com.alibaba.fastjson.JSON;
import com.example.apiencryption.util.Security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 3d3n
 * @Date: 2021
 */
@Component
@ControllerAdvice(basePackages = "com.example.apiencryption.controller")
public class EncryResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        //Get HttpServletRequest through the implementation class ServletServerHttpRequest of ServerHttpRequest
        ServletServerHttpRequest sshr = (ServletServerHttpRequest) serverHttpRequest;
        //The purpose of getting request here is to get an object set in the interceptor. It is required by my project and can be ignored.
        HttpServletRequest request = sshr.getServletRequest();

        String returnStr = "";

        try {
            //Tell the frontend that the data is encrypted
            serverHttpResponse.getHeaders().add("encry", "true");
            String srcData = JSON.toJSONString(obj);
            //encryption
            returnStr = Security.encrypt(srcData);
            log.info("interface={}, Raw data={}, Encrypted data={}", request.getRequestURI(), srcData, returnStr);

        } catch (Exception e) {
            log.error("exception！", e);
        }
        return returnStr;
    }

}
