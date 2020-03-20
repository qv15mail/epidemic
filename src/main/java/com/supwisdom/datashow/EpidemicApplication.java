package com.supwisdom.datashow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;
import javax.servlet.annotation.MultipartConfig;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.supwisdom.datashow.*.mapper")
public class EpidemicApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpidemicApplication.class, args);
    }

//    @Bean
//    MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setLocation("/home/temp");
//        return factory.createMultipartConfig();
//    }
//    @Bean
//    public ConfigurableServletWebServerFactory configurableServletWebServerFactory() {
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//        factory.addContextCustomizers(context -> {
//            SecurityConstraint securityConstraint = new SecurityConstraint();
//            securityConstraint.setUserConstraint("CONFIDENTIAL");
//            SecurityCollection collection = new SecurityCollection();
//            collection.addPattern("/*");
//            collection.addMethod("HEAD");
//            collection.addMethod("PUT");
//            collection.addMethod("DELETE");
//            collection.addMethod("OPTIONS");
//            collection.addMethod("TRACE");
//            collection.addMethod("COPY");
//            collection.addMethod("SEARCH");
//            collection.addMethod("PROPFIND");
//            securityConstraint.addCollection(collection);
//            context.addConstraint(securityConstraint);
//        });
//        return factory;
//    }
}
