package uz.pdp.store.enitity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;
import uz.pdp.store.enitity.base.Auditable;


import java.time.LocalDate;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction(value = "deleted = false")
@Table(name = "drug", schema = "public")
public class Product extends Auditable {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description_key", unique = true, nullable = false)
    private String description;

    @Column(name = "country_name", nullable = false)
    private String countryName;
    @OneToOne
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;

    @Column(name = "produced_date", nullable = false)
    private LocalDate producedDate;

    @Column(name = "original_price", nullable = false)
    private Double originalPrice;


    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

}
