package uz.pdp.store.dto.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AuditableDto extends GenericDTO {
    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private Long createdBy;

    private Long modifiedBy;

}
