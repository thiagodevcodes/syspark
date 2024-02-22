package com.park.syspark.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Value("${openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact1 = new Contact();
        contact1.setEmail("alexandre.loiola.741@gmail.com");
        contact1.setName("Alexandre Loiola");
        contact1.setUrl("https://www.alexandreloiola.galatus.com.br");

        Contact contact2 = new Contact();
        contact2.setEmail("euthiagosrodrigues@gmail.com");
        contact2.setName("Thiago Silva");
        contact2.setUrl("https://thiagodev.vercel.app");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        String description = """
                This API exposes endpoints to manage tutorials.

                ## Contacts

                - %s
                  - Email: <a href='mailto:%s'>%s</a>
                  - Website: <a href='%s'>%s</a>
                - %s
                  - Email: <a href='mailto:%s'>%s</a>
                  - Website: <a href='%s'>%s</a>
                """.formatted(contact1.getName(), contact1.getEmail(), contact1.getEmail(), contact1.getUrl(), contact1.getUrl(), contact2.getName(), contact2.getEmail(), contact2.getEmail(), contact2.getUrl(), contact2.getUrl());


        Info info = new Info()
                .title("Parking System API")
                .version("1.0")
                .description(description)
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
