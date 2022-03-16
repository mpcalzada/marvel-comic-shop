package com.mcalzada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"com.mcalzada", "com.mcalzada.controllers", "com.mcalzada.config"})
public class Swagger2SpringBoot
{

    public static void main(String[] args) throws RuntimeException
    {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }
}
