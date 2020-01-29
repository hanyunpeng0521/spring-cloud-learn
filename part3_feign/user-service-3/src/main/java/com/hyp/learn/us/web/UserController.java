package com.hyp.learn.us.web;

import com.hyp.learn.us.domain.User;
import com.hyp.learn.us.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author hyp
 * Project name is spring-cloud-learn
 * Include in com.hyp.learn.us.web
 * hyp create at 20-1-27
 **/
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable Long id) {
        Optional<User> userOptional =
                userRepository.findById(id);
        return userOptional.orElse(null);
    }
}
