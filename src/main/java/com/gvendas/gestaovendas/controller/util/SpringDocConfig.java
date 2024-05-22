package com.gvendas.gestaovendas.controller.util;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Gestão de vendas")
                        .description("Sistema de gestão de vendas")
                        .version("1.0.0")
                );
    }
}
