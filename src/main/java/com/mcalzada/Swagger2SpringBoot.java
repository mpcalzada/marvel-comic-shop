package com.mcalzada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.mcalzada", "com.mcalzada.controllers"})
public class Swagger2SpringBoot
{

    public static void main(String[] args) throws RuntimeException
    {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }
}
