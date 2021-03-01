package com.example.apiencryption.controller;

import com.alibaba.fastjson.JSON;
import com.example.apiencryption.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping(value = "/sendencdata")
    public Result sendEncData(@RequestBody Object object) {
        Result result = null;
        try {
            log.info("Parameters received by the controller object={}", JSON.toJSONString(object));
            result = Result.createResult().setSuccess(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
