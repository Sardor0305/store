package uz.pdp.store.dto.login;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SessionDto {

    private String accessToken;

    private String refreshToken;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss.SSS")
    private LocalDateTime accessTokenExpiry;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss.SSS")
    private LocalDateTime refreshTokenExpiry;

    private String tokenType;

    public static SessionDto of(
            String accessToken,
            LocalDateTime accessTokenExpiry
    ) {
        return of(accessToken, null, accessTokenExpiry, null);
    }

    public static SessionDto of(
            String accessToken,
            String refreshToken,
            LocalDateTime accessTokenExpiry,
            LocalDateTime refreshTokenExpiry
    ) {
        return new SessionDto(accessToken, refreshToken, accessTokenExpiry, refreshTokenExpiry, "Bearer");
    }

}
