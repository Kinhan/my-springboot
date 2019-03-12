package com.ljh.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableConfigurationProperties
@tk.mybatis.spring.annotation.MapperScan("com.ljh.myspringboot.mapper")
@EnableSwagger2
public class MySpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringbootApplication.class, args);
    }
}
