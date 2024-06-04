package uz.pdp.store.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.store.annotations.Password;
import uz.pdp.store.annotations.PhoneNumber;
import uz.pdp.store.dto.base.BaseDTO;
import uz.pdp.store.unitls.MessageKey;

import javax.management.relation.Role;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserCreateDTO implements BaseDTO {
    @PhoneNumber
    private String phone;

    @Password
    private String password;

    @Size(min = 3, max = 30)
    @NotBlank(message = MessageKey.INVALID_FIRSTNAME)
    private String firstName;

    @Size(min = 3, max = 30)
    @NotBlank(message = MessageKey.INVALID_LASTNAME)
    private String lastName;


    @NotBlank(message = MessageKey.INVALID_ROLE)
    private Role role;

}
