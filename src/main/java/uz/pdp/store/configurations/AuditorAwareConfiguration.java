package uz.pdp.store.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import uz.pdp.store.enitity.base.SecurityAuditorAware;


@Configuration
public class AuditorAwareConfiguration {

    @Bean
    AuditorAware<Long> auditorAware() {
        return new SecurityAuditorAware();
    }

}
