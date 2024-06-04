package uz.pdp.store.jwt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    @Positive(message = "Access token expiry must be positive value")
    private long accessTokenExpiry;

    @Positive(message = "Refresh token expiry must be positive value")
    private long refreshTokenExpiry;

    @NotBlank(message = "Secret key can not be empty")
    private String secretKey;

}
