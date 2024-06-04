package uz.pdp.store.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.store.annotations.Password;
import uz.pdp.store.dto.base.BaseDTO;
import uz.pdp.store.unitls.MessageKey;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PasswordDTO implements BaseDTO {

    @Password
    private String currentPassword;

    @Password(message = MessageKey.INVALID_NEW_PASSWORD)
    private String newPassword;

    @Password(message = MessageKey.INVALID_CONFIRM_PASSWORD)
    private String confirmPassword;

}
