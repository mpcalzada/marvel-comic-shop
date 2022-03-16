package com.mcalzada.config;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2022-03-15T06:36:53.230Z")

@Configuration
public class SwaggerDocumentationConfig
{

    ApiInfo apiInfo()
    {
        return new ApiInfoBuilder()
              .title("Marvel Comics Shop")
              .description("SpringBoot REST API for retrieview marvel heroes artist information")
              .license("Apache 2.0")
              .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
              .termsOfServiceUrl("")
              .version("1.0.0")
              .contact(new Contact("Marco Calzada", "https://github.com/mpcalzada", "mccalzada@outlook.com"))
              .build();
    }

    @Bean
    public Docket customImplementation()
    {
        return new Docket(DocumentationType.SWAGGER_2)
              .select()
              .apis(RequestHandlerSelectors.basePackage("com.mcalzada.controllers"))
              .build()
              .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
              .directModelSubstitute(java.time.OffsetDateTime.class, java.util.Date.class)
              .genericModelSubstitutes(Optional.class)
              .apiInfo(apiInfo());
    }

}
