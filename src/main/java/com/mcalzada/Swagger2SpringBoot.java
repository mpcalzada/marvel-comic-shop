package com.mcalzada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Swagger2SpringBoot is the main class which performs IoC to {@link SpringApplication}
 *
 * @author Marco Calzada
 * @version 1.0
 */
@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.mcalzada", "com.mcalzada.controllers"})
public class Swagger2SpringBoot
{

    /**
     * Main method
     *
     * @param args program arguments
     * @throws RuntimeException throws an exception if SpringConfiguration isn’t valid
     */
    public static void main(String[] args) throws RuntimeException
    {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }
}
