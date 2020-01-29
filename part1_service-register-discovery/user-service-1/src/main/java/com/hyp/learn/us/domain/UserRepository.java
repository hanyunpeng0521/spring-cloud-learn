package com.hyp.learn.us.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hyp
 * Project name is spring-cloud-learn
 * Include in com.hyp.learn.us.domain
 * hyp create at 20-1-27
 **/
//@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
