package com.hyp.learn.sentinel.web;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.hyp.learn.sentinel.ExceptionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hyp
 * Project name is spring-cloud-learn
 * Include in com.hyp.learn.nacos.web
 * hyp create at 20-2-2
 **/
@RestController
@RequestMapping("/sentinel")
public class ConfigController {

    private String appName = "cloud-sentinel";

    @SentinelResource("resource")
    @RequestMapping("/hello")
    public Map<String, Object> hello() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("appName", appName);
        map.put("method", "hello");
        return map;
    }

    /**
     * 通过控制台配置URL 限流
     *
     * @return
     */
    @RequestMapping("/test")
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("appName", appName);
        map.put("method", "test");
        return map;
    }

    //通过注解限流并自定义限流逻辑
    @SentinelResource(value = "resource2", blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
    @RequestMapping("/test2")
    public Map<String,Object> test2() {
        Map<String,Object> map=new HashMap<>();
        map.put("method","test2");
        map.put("msg","自定义限流逻辑处理");
        return  map;
    }
}
