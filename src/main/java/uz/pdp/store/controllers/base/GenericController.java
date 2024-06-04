package uz.pdp.store.controllers.base;


import jakarta.validation.Valid;
import uz.pdp.store.criteria.BaseCriteria;
import uz.pdp.store.dto.base.BaseDTO;
import uz.pdp.store.dto.base.GenericDTO;



@Valid
public interface GenericController<D extends GenericDTO, CD extends BaseDTO, UD extends GenericDTO, C extends BaseCriteria>
        extends GenericSelectableController<D, C>, GenericModifiableController<D, CD, UD> {
}
