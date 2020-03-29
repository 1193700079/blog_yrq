package com.yrq.blog;

import com.yrq.blog.utils.MyBanner;
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

    public static void main(String[] args) {
//        SpringApplication.run(BlogApplication.class, args);
        SpringApplication newRun= new SpringApplication(BlogApplication.class);
        newRun.setBanner(new MyBanner());
        newRun.run(args);
    }

}

