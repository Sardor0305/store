package uz.pdp.store.service;

import lombok.RequiredArgsConstructor;

import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import uz.pdp.store.dto.login.LoginDTO;
import uz.pdp.store.dto.login.RefreshTokenDTO;
import uz.pdp.store.dto.login.SessionDto;
import uz.pdp.store.enitity.User;
import uz.pdp.store.exeptions.NotFoundException;
import uz.pdp.store.jwt.JwtService;
import uz.pdp.store.service.base.BaseService;
import uz.pdp.store.unitls.MessageKey;

@Service
@RequiredArgsConstructor
public class AuthService implements BaseService {

    private final AuthenticationManager authManager;

    private final JwtService jwtService;

    private final UserService userService;

    @SneakyThrows
    public SessionDto login(LoginDTO dto) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getPhone(), dto.getPassword())
            );
            User user = (User) authentication.getPrincipal();
            if (!user.isEnabled()) {
                throw new NotFoundException(MessageKey.MESSAGE_NOT_FOUND);
            }
            return jwtService.getSessionDto(user);
        } catch (BadCredentialsException e) {
            throw new BadRequestException(MessageKey.BAD_CREDENTIALS);
        }
    }

    @SneakyThrows
    public SessionDto refreshToken(RefreshTokenDTO refreshTokenDTO) {
        String refreshToken = refreshTokenDTO.getRefreshToken();
        if (jwtService.isTokenExpired(refreshToken)) {
            throw new BadRequestException("Invalid refresh token");
        }
        String username = jwtService.extractUsername(refreshToken);
        User user = (User) userService.loadUserByUsername(username);
        if (!user.isEnabled()) {
            throw new NotFoundException(MessageKey.BAD_CREDENTIALS);
        }
        return SessionDto.of(jwtService.generateAccessToken(user), jwtService.getAccessTokenExpiry());
    }

}
