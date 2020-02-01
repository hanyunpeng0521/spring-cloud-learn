package com.hyp.learn.us.web;

import com.hyp.learn.us.domain.DemoReq;
import com.hyp.learn.us.domain.DemoResp;
import com.hyp.learn.us.domain.User;
import com.hyp.learn.us.domain.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author hyp
 * Project name is spring-cloud-learn
 * Include in com.hyp.learn.us.web
 * hyp create at 20-1-27
 **/
@Api(tags = "用户操作接口")
@RestController("user")
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "findById")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable Long id) {
        Optional<User> userOptional =
                userRepository.findById(id);
        return userOptional.orElse(null);
    }

    @GetMapping("/hello")
    @ApiOperation(value = "demo示例")
    public DemoResp hello(DemoReq demoReq) {
        log.info("DemoReq:{}", demoReq);

        return DemoResp.builder()
                .code(demoReq.getCode())
                .name(demoReq.getName())
                .remark(demoReq.getRemark())
                .build();
    }
}
