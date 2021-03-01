package com.example.apiencryption.config;


import com.example.apiencryption.util.HttpMessage;
import com.example.apiencryption.util.Security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

/**
 * @Author: 3d3n
 * @Date: 2021
 */
@Component
@ControllerAdvice(basePackages = "com.example.apiencryption.controller")
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> selectedConverterType) throws IOException {
        String httpBody = null;
        try {
            //Encryption operation
            log.info("Original request data received={}", inputMessage.toString());
            httpBody = decryptBody(inputMessage);

            log.info("Data after decryption={}", httpBody);
        } catch (Exception e) {
            log.error("exception！", e);
            e.printStackTrace();
        }

        System.out.println(inputMessage.getHeaders().toString());
        return new HttpMessage(new ByteArrayInputStream(httpBody.getBytes()), inputMessage.getHeaders());
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }


    @Override
    public Object handleEmptyBody(@Nullable Object var1, HttpInputMessage var2, MethodParameter var3, Type var4, Class<? extends HttpMessageConverter<?>> var5) {
        return var1;
    }


    /**
     * Decrypt message body, 3des parsing (cbc mode)
     *
     * @param inputMessage message body
     * @return Clear text
     */
    private String decryptBody(HttpInputMessage inputMessage) throws IOException {
        InputStream encryptStream = inputMessage.getBody();
        String encryptBody = StreamUtils.copyToString(encryptStream, Charset.defaultCharset());
        return Security.decrypt(encryptBody);
    }

}
