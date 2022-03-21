package com.mcalzada.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenApiConfig is a class used for configuring OpenAPI docs info
 *
 * @author Marco Calzada
 * @version 1.0
 */
@Configuration
public class OpenApiConfig
{

    /**
     * This class builds a bean with the application information to be shown as OpenAPI docs
     *
     * @return OpenAPI info bean
     */
    @Bean
    OpenAPI apiInfo()
    {
        return new OpenAPI()
              .info(new Info()
                    .title("Marvel Comics Shop")
                    .description("SpringBoot REST API for retrieview marvel heroes artist information")
                    .version("v1.0")
                    .contact(new Contact()
                          .name("Marco Calzada")
                          .url("https://github.com/mpcalzada")
                          .email("mccalzada@outlook.com"))
                    .termsOfService("")
                    .license(new License().name("MIT").url("https://github.com/mpcalzada/marvel-comic-shop/blob/feature/controller-services/LICENCE"))
              );
    }
}
