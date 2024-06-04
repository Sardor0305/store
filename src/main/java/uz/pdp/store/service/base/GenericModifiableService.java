package uz.pdp.store.service.base;


import uz.pdp.store.dto.base.BaseDTO;
import uz.pdp.store.dto.base.GenericDTO;

public interface GenericModifiableService<D extends GenericDTO, CD extends BaseDTO, UD extends GenericDTO> {

    D create(CD dto);

    D update(Long id, UD dto);

    void delete(Long id);

}
