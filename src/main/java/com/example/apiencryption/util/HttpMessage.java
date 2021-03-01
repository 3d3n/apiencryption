package com.example.apiencryption.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

//
//@AllArgsConstructor
//@NoArgsConstructor
public class HttpMessage implements HttpInputMessage {
    private InputStream body;
    private HttpHeaders httpHeaders;

    public HttpMessage(InputStream body, HttpHeaders httpHeaders) {
        this.body = body;
        this.httpHeaders = httpHeaders;
    }

    @Override
    public InputStream getBody() {
        return this.body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return this.httpHeaders;
    }

}
