package uz.pdp.store.service.base;


import uz.pdp.store.criteria.BaseCriteria;
import uz.pdp.store.dto.base.BaseDTO;
import uz.pdp.store.dto.base.GenericDTO;
import uz.pdp.store.enitity.base.BaseEntity;

public interface GenericService<E extends BaseEntity, D extends GenericDTO, CD extends BaseDTO, UD extends GenericDTO, C extends BaseCriteria>
        extends GenericSelectableService<E, D, C>,
        GenericModifiableService<D, CD, UD> {
}
