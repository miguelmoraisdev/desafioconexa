package com.conexatest.miguel.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .addServersItem(new Server().url("http:localhost:8081"))
                .info(this.metaData());
    }

    private Info metaData() {
        return new Info()
                .title("Conexa Test 1")
                .description("Codes for the Conexa Test 1")
                .version("1.0.0");
    }

}