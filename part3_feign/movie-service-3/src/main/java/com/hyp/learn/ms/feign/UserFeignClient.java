package com.hyp.learn.ms.feign;

import com.hyp.learn.ms.domain.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author hyp
 * Project name is spring-cloud-learn
 * Include in com.hyp.learn.ms.feign
 * hyp create at 20-1-27
 **/
@FeignClient(name = "user-service", configuration = FeignConfiguration.class)
// 如果不使用服务发现，则可以如下直接指定请求的URL
//@FeignClient(name = "user-service", url = "http://localhost:8000/")
public interface UserFeignClient {
    /**
     * 使用feign自带的注解@RequestLine
     *
     * @param id 用户id
     * @return 用户信息
     * @see https://github.com/OpenFeign/feign
     */
    @RequestLine("GET /{id}")
    User findById(@Param("id") Long id);
//    @GetMapping(value = "/{id}")
//    User findById(@PathVariable("id") Long id);
//
//    // 针对两个参数的情况
//    @GetMapping(value = "/get")
//    User getUserByIdAndUserName(@RequestParam("id") Long id, @RequestParam("username") String username);
//
//    // 针对多个参数的情况 => 使用Map
//    @GetMapping(value = "/get")
//    User getUserByMap(@RequestParam Map<String, Object> map);
//
//    // 针对Post的情况
//    @PostMapping(value = "/post")
//    User postUser(@RequestBody User user);
}
