package com.hyp.learn.ms.web;

import com.hyp.learn.ms.domain.User;
import com.hyp.learn.ms.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author hyp
 * Project name is spring-cloud-learn
 * Include in com.hyp.learn.ms.web
 * hyp create at 20-1-27
 **/
@RestController
public class MovieController {

    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo() {
        return discoveryClient.getInstances("user-service");
    }

    /**
     * 获取所有服务
     */
    @RequestMapping("/services")
    public Object services() {
        // 获取对应服务名称的所有实例信息
        return discoveryClient.getInstances("user-service");
    }

    /**
     * 从所有服务中选择一个服务（轮询）
     */
    @RequestMapping("/discover")
    public Object discover() {
        return loadBalancer.choose("user-service").getUri().toString();
    }

    @RequestMapping("/call/{id}")
    public String call(@PathVariable Long id) {
        ServiceInstance serviceInstance = loadBalancer.choose("user-service");
        System.out.println("服务地址：" + serviceInstance.getUri());
        System.out.println("服务名称：" + serviceInstance.getServiceId());

        String callServiceResult = restTemplate.getForObject(serviceInstance.getUri().toString() + "/" + id, String.class);
        System.out.println(callServiceResult);
        return callServiceResult;
    }


    @Autowired
    private UserFeignClient userFeignClient;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }


}