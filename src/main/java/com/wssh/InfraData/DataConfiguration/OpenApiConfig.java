package com.wssh.InfraData.DataConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
       return new OpenAPI()
        .info(new Info()
            .title("Teste")
            .version("v1")
            .description("Teste")
            .termsOfService("")
            .license(new License()
                .name("Apache 2.0")
                .url("")));
    }

}
