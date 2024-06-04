package uz.pdp.store.controllers.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import uz.pdp.store.criteria.BaseCriteria;
import uz.pdp.store.dto.base.GenericDTO;
import uz.pdp.store.response.ResponseData;

import java.util.List;


public interface GenericSelectableController<D extends GenericDTO, C extends BaseCriteria> {

    ResponseEntity<ResponseData<D>> get(Long id);

    ResponseEntity<ResponseData<List<D>>> getAll(C criteria);

    ResponseEntity<ResponseData<Page<D>>> getAll(Pageable pageable, C criteria);

}
