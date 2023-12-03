package kr.co.kshproject.webDemo.Common;

import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi commonApi() {
        return GroupedOpenApi.builder()
                .group("api")
                .addOpenApiCustomiser(serverCustomizer())
                .packagesToScan("kr.co.kshproject.webDemo")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenApiCustomiser serverCustomizer() {

        return openApi -> {

            openApi.addServersItem(new Server().url("https://ddubi.site/").description("for testing"));
            openApi.addServersItem(new Server().url("http://localhost:8080").description("for local usages"));
        };
    }

}