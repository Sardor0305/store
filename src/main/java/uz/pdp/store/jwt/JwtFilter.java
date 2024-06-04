package uz.pdp.store.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.store.constants.Methods;
import uz.pdp.store.response.ResponseBuilder;
import uz.pdp.store.service.UserService;
import uz.pdp.store.unitls.FilterUtils;
import uz.pdp.store.unitls.MessageKey;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;
    private final ResponseBuilder responseBuilder;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String token = FilterUtils.getTokenFromRequest(request);
            if (!jwtService.isBearer(token)) {
                FilterUtils.errorResponse(response, responseBuilder.unauthorized(request));
                return;
            }
            token = token.substring(7);
            final String username = jwtService.extractUsername(token);
            final UserDetails userDetails = userService.loadUserByUsername(username);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
            );
        } catch (ExpiredJwtException e) {
            FilterUtils.errorResponse(response, responseBuilder.unauthorized(request, MessageKey.TOKEN_EXPIRED));
            return;
        } catch (JwtException e) {
            FilterUtils.errorResponse(response, responseBuilder.unauthorized(request, e.getMessage()));
            return;
        } catch (UsernameNotFoundException e) {
            FilterUtils.errorResponse(response, responseBuilder.badRequest(request, MessageKey.USERNAME_PASSWORD_INCORRECT));
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().contains("swagger") || Methods.NON_FILTER_METHODS.contains(request.getRequestURI());
    }

}
