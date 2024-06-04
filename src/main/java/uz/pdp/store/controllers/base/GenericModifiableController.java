package uz.pdp.store.controllers.base;

import org.springframework.http.ResponseEntity;
import uz.pdp.store.dto.base.BaseDTO;
import uz.pdp.store.dto.base.GenericDTO;
import uz.pdp.store.response.ResponseData;
import uz.pdp.store.response.ResponseMessage;


public interface GenericModifiableController<D extends GenericDTO, CD extends BaseDTO, UD extends GenericDTO> {

    ResponseEntity<ResponseData<D>> create(CD dto);

    ResponseEntity<ResponseData<D>> update(Long id, UD dto);

    ResponseEntity<ResponseData<ResponseMessage>> delete(Long id);

}
