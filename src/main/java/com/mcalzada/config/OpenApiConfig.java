package com.mcalzada.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig
{

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
