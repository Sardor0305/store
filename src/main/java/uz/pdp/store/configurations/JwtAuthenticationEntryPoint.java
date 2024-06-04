package uz.pdp.store.configurations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import uz.pdp.store.response.ResponseBuilder;
import uz.pdp.store.unitls.FilterUtils;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ResponseBuilder responseBuilder;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        FilterUtils.errorResponse(response, responseBuilder.unauthorized(request));
    }

}
