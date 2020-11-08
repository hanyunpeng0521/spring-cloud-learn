package com.hyp.learn.ms.feign;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author hyp
 * Project name is spring-cloud-learn
 * Include in com.hyp.learn.ms.feign
 * hyp create at 20-1-27
 **/
/*
 * 该类为Feign的配置类
 * 注意：该类不应该在主应用程序上下文的@ComponentScan中
 * @author EdisonZhou
 */
//@Configuration
public class FeignConfiguration {
    /*
     * 将契约改为feign原生的默认契约，这样就可以使用feign自带的注解了
     * @return 默认的feign契约
     */
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        /*
         * Logger.Level 可选值：
         * NONE: 不记录任何日志（默认值）
         * BASIC: 仅记录请求方法、URL、响应状态码以及执行时间
         * HEADERS: 记录BASIC级别的基础之上，记录请求和响应的header
         * FULL: 记录请求和响应的header，body和元数据
         */
        return Logger.Level.BASIC;
    }
}
