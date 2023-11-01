package com.weifeng.musicprogram;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
热更新，热加载
setting---build---compile----build****automally
* */
@SpringBootApplication
@Configuration
@EnableSwagger2
@MapperScan("com.weifeng.musicprogram.Dao")
public class MusicProgramApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicProgramApplication.class, args);
    }

}
