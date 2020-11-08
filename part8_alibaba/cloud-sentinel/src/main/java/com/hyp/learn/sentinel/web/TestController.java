package com.hyp.learn.sentinel.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyp
 * Project name is spring-cloud-learn
 * Include in com.hyp.learn.sentinel.web
 * hyp create at 20-2-2
 **/
@Slf4j
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        return "hanyunpeng0521.github.io";
    }
}
