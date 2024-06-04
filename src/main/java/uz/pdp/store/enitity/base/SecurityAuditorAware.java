package uz.pdp.store.enitity.base;

import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import uz.pdp.store.unitls.ThreadLocalSingleton;


import java.util.Optional;

@Component
public class SecurityAuditorAware implements AuditorAware<Long> {

    @Override
    @NonNull
    public Optional<Long> getCurrentAuditor() {
        return Optional.ofNullable(ThreadLocalSingleton.getUser().getId());
    }

}
