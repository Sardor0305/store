package uz.pdp.store.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.store.dto.base.AuditableDto;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ProductDTO extends AuditableDto {
    private String name;

    private double originalPrice;


    private Long drugInfoId;
}
