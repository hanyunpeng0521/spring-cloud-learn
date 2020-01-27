package com.hyp.learn.essn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceHa2Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceHa2Application.class, args);
    }

}
