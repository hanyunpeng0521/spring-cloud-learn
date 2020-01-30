package com.hyp.learn.zuul.service;

import com.hyp.learn.zuul.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

/**
 * @author hyp
 * Project name is spring-cloud-learn
 * Include in com.hyp.learn.zuul.service
 * hyp create at 20-1-30
 **/
@Service
public class AggregationService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getUserById(Long id) {
        // create one observer
        return Observable.create(observer -> {
            // request user service /{id}
            User user = restTemplate.getForObject("http://user-service/{id}", User.class, id);
            observer.onNext(user);
            observer.onCompleted();
        });
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getMovieByUserId(Long id) {
        // create one observer
        return Observable.create(observer -> {
            // request movie service /user/{id}
            User movieUser = restTemplate.getForObject("http://movie-service/user/{id}", User.class, id);
            observer.onNext(movieUser);
            observer.onCompleted();
        });
    }

    public User fallback(Long id) {
        User user = new User();
        user.setId(-1L);
        user.setUsername("Default Username");

        return user;
    }
}
