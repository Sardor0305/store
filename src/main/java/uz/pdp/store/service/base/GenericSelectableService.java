package uz.pdp.store.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.store.criteria.BaseCriteria;
import uz.pdp.store.dto.base.GenericDTO;
import uz.pdp.store.enitity.base.BaseEntity;


import java.util.List;


public interface GenericSelectableService<E extends BaseEntity, D extends GenericDTO, C extends BaseCriteria> {

    E findById(Long id);

    D get(Long id);

    List<D> getAll(C criteria);

    Page<D> getAll(C criteria, Pageable pageable);

}
