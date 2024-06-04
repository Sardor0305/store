package uz.pdp.store.enitity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.store.enitity.base.BaseEntity;
import uz.pdp.store.enums.ContentType;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attachment", schema = "public")
public class Attachment extends BaseEntity {

    @Column(name = "content_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ContentType contentType;

    @Column(name = "size", nullable = false)
    private long size;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "generated_name", nullable = false)
    private String generatedName;

    @Column(name = "url", nullable = false)
    private String url;

}
