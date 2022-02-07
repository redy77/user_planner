package ru.victor.user_planner.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableWebMvc
@EnableSwagger2
@Configuration
public class SwaggersConfigs {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info().title("User planner")
                        .version("01")
                        .description("РSchedule of work shifts"));
    }
}