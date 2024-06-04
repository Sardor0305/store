package uz.pdp.store;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.pdp.store.jwt.JwtProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = JwtProperties.class)
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@OpenAPIDefinition
public class StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

}
