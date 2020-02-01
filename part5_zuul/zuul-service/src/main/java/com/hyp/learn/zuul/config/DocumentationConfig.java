package com.hyp.learn.zuul.config;

import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 缺点:重复配置
 * 优点：可以动态显示注册服务
 * @author hyp
 * Project name is spring-cloud-learn
 * Include in com.hyp.learn.zuul.config
 * hyp create at 20-1-31
 **/
//@Component
//@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {

    private final RouteLocator routeLocator;

    public DocumentationConfig(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        //利用routeLocator动态引入微服务
        List<SwaggerResource> resources = new ArrayList<>();
        resources.add(swaggerResource("zuul-gateway", "/v2/api-docs", "1.0"));
        //循环 使用Lambda表达式简化代码
        routeLocator.getRoutes().forEach(route -> {
            //动态获取
            resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs"), "1.0"));
        });
        resources.forEach((it) -> {
            System.out.println(it.getName() + "  " + it.getLocation());
        });
        //也可以直接 继承 Consumer接口
//		routeLocator.getRoutes().forEach(new Consumer<Route>() {
//
//			@Override
//			public void accept(Route t) {
//				// TODO Auto-generated method stub
//
//			}
//		});
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
