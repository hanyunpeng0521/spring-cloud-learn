package com.hyp.learn.essn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceSn6Application {

    public static void main(String[] args) {
        SpringApplication.run(com.hyp.learn.essn.EurekaServiceSn6Application.class, args);
    }

}
