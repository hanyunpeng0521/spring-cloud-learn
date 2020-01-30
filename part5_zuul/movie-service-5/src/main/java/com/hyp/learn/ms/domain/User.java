package com.hyp.learn.ms.domain;

import java.math.BigDecimal;

/**
 * @author hyp
 * Project name is spring-cloud-learn
 * Include in com.hyp.learn.ms.domain
 * hyp create at 20-1-27
 **/
public class User {

    private Long id;

    private String username;

    private String realname;

    private Integer age;

    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
