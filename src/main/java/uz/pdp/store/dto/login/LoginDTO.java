package uz.pdp.store.dto.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.store.annotations.Password;
import uz.pdp.store.annotations.PhoneNumber;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDTO {
    @PhoneNumber
    private String phone;

    @Password
    private String password;

}
