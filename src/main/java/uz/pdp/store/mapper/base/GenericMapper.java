package uz.pdp.store.mapper.base;


import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uz.pdp.store.dto.base.BaseDTO;
import uz.pdp.store.dto.base.GenericDTO;
import uz.pdp.store.enitity.base.BaseEntity;

public interface GenericMapper<E extends BaseEntity, D extends GenericDTO, CD extends BaseDTO, UD extends GenericDTO>
        extends BaseMapper {

    D fromEntity(E entity);

    E toEntity(D dto);

    E toEntity(CD dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toEntity(UD dto, @MappingTarget E entity);

    E toDeleteEntity(E entity);

}
