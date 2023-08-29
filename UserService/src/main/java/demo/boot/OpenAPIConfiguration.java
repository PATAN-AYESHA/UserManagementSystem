package demo.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfiguration {

	@Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI().info(new Info().title("User Management System")
                                 .description("User Service")
                                 .version("2.0"));
    }
}
