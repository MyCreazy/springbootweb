package com.mycreazy.springbootweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created with IntelliJ IDEA.
 * Time: 下午5:27
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.mycreazy.springbootweb"})
@MapperScan("com.mycreazy.springbootweb.dao")
@ServletComponentScan
@EnableTransactionManagement
public class SpringbootWebApp  {
    /**
     * main函数
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebApp.class, args);
    }
}
