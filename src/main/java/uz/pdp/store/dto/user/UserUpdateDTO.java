package uz.pdp.store.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.store.annotations.PhoneNumber;
import uz.pdp.store.annotations.ValueOfEnum;
import uz.pdp.store.dto.base.GenericDTO;
import uz.pdp.store.enums.Language;
import uz.pdp.store.unitls.MessageKey;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserUpdateDTO extends GenericDTO {
    @PhoneNumber
    private String phone;

    @Size(min = 3, max = 30)
    @NotBlank(message = MessageKey.INVALID_FIRSTNAME)
    private String firstName;

    @Size(min = 3, max = 30)
    @NotBlank(message = MessageKey.INVALID_LASTNAME)
    private String lastName;

    @ValueOfEnum(enumClass = Language.class)
    @NotBlank(message = MessageKey.INVALID_LANGUAGE)
    private String language;


    @NotBlank(message = MessageKey.INVALID_ROLE)
    private Long roleId;

}
