package com.yrq.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.*;

//@RestController
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@MapperScan("com.yrq.blog.mapper")
public class BlogApplication {

//    @RequestMapping("/")
//    String home() {
//        return "Hello World!";
//    }
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}

