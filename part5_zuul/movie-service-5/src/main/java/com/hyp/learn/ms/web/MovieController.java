package com.hyp.learn.ms.web;

import com.hyp.learn.ms.domain.User;
import com.hyp.learn.ms.feign.UserFeignClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hyp
 * Project name is spring-cloud-learn
 * Include in com.hyp.learn.ms.web
 * hyp create at 20-1-27
 **/
@Api(tags = "电影")
@RestController("movie")
@RequestMapping("movie")
public class MovieController {

    @Autowired
    private UserFeignClient userFeignClient;


    //    @HystrixCommand(fallbackMethod = "findByIdFallback")
    @ApiOperation(value = "查询用户信息")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo() {
        return discoveryClient.getInstances("user-service");
    }

//    public User findByIdFallback(Long id){
//        User user = new User();
//        user.setId(-1L);
//        user.setUsername("Default User");
//
//        return user;
//    }
}