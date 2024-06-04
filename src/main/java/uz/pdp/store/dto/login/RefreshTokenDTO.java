package uz.pdp.store.dto.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.store.unitls.MessageKey;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class RefreshTokenDTO {
    @NotBlank(message = MessageKey.INVALID_REFRESH_TOKEN)
    private String refreshToken;

}
