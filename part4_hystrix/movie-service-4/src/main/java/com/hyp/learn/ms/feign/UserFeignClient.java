package com.hyp.learn.ms.feign;

import com.hyp.learn.ms.domain.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author hyp
 * Project name is spring-cloud-learn
 * Include in com.hyp.learn.ms.feign
 * hyp create at 20-1-27
 **/
//@FeignClient(name = "user-service", configuration = FeignConfiguration.class)

//fallback
//@FeignClient(name = "user-service", fallback = FeignClientFallback.class)
//fallbackFactory
@FeignClient(name = "user-service", fallbackFactory = FeignClientFallbackFactory.class)

// 如果不使用服务发现，则可以如下直接指定请求的URL
//@FeignClient(name = "user-service", url = "http://localhost:8000/")
public interface UserFeignClient {
    //    /**
//     * 使用feign自带的注解@RequestLine
//     *
//     * @param id 用户id
//     * @return 用户信息
//     * @see https://github.com/OpenFeign/feign
//     */
//    @RequestLine("GET /{id}")
//    User findById(@Param("id") Long id);
    @GetMapping(value = "/{id}")
    User findById(@PathVariable("id") Long id);

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

//@Component
//class FeignClientFallback implements UserFeignClient {
//    @Override
//    public User findById(Long id) {
//        User user = new User();
//        user.setId(-1L);
//        user.setUsername("Default User");
//
//        return user;
//    }
//}

@Component
class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(FeignClientFallbackFactory.class);

    @Override
    public UserFeignClient create(Throwable cause) {
        return new UserFeignClient() {
            @Override
            public User findById(Long id) {
                /*
                 * 日志最好放在各个fallback中，而不要直接放在create方法中
                 * 否则在引用启动时，就会打印该日志
                 */
                FeignClientFallbackFactory.LOGGER.info("sorry, fallback. reason was: ", cause);
                User user = new User();
                user.setId(-1L);
                user.setUsername("Default Username");

                return user;
            }
        };
    }
}
