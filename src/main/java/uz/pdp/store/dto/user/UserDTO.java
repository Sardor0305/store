package uz.pdp.store.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.store.dto.base.AuditableDto;
import uz.pdp.store.enums.Language;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserDTO extends AuditableDto {
    private String phone;

    private String firstName;

    private String lastName;

//    private UserRole role;

    private Language language;

}
