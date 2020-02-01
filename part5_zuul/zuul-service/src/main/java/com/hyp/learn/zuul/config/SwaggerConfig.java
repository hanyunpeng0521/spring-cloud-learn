package com.hyp.learn.zuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hyp
 * Project name is spring-cloud-learn
 * Include in com.hyp.learn.zuul.config
 * hyp create at 20-1-31
 **/
@Configuration
@EnableSwagger2
@Primary
public class SwaggerConfig {

    //是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled).
                        select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("com.hyp.learn.zuul"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any()).build().pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("XXXXX系统")
                .description("XXXX系统接口文档说明")
                .termsOfServiceUrl("http://localhost:5000")
                .version("1.0")
                .build();
    }


    //利用注入Zuul的配置文件，实现对路由源服务API的测试
    @Autowired
    ZuulProperties properties;

    /**
     * 改进：不会重复注册
     * 缺点：必须是配置的路由
     * @return
     */
    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        return () -> {
            List<SwaggerResource> resources = new ArrayList<>();
            resources.add(createResource("zuul-gateway", "/v2/api-docs", "1.0"));

            properties.getRoutes().values().stream()
                    .forEach(route ->
                    {
                        resources
                                .add(createResource(route.getServiceId(), "/api"+route.getPath().replace("**", "v2/api-docs"), "1.0"));
                        System.out.println(route);
                    });
            return resources;
        };
    }

    private SwaggerResource createResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}
