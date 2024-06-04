package uz.pdp.store.configurations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import uz.pdp.store.enitity.User;
import uz.pdp.store.enums.Language;
import uz.pdp.store.response.ResponseBuilder;
import uz.pdp.store.service.UserService;
import uz.pdp.store.unitls.FilterUtils;
import uz.pdp.store.unitls.ThreadLocalSingleton;


import java.io.IOException;
import java.util.Objects;



@Component
@RequiredArgsConstructor
public class HandlerInterceptorConfiguration implements HandlerInterceptor {

    private final UserService userService;
    private final ResponseBuilder responseBuilder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (Objects.isNull(authentication) || authentication.getPrincipal().equals("anonymousUser")) {
                String acceptLanguage = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
                if (Objects.nonNull(acceptLanguage)) {
                    ThreadLocalSingleton.getUser().setLanguage(Language.fromValue(acceptLanguage));
                }
                return true;
            }
            ThreadLocalSingleton.setUser((User) authentication.getPrincipal());

            return true;
        } catch (Exception e) {
            FilterUtils.errorResponse(response, responseBuilder.unauthorized(request));
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ThreadLocalSingleton.removeAll();
    }

}
