package uz.pdp.store.specification;

import org.springframework.data.jpa.domain.Specification;
import uz.pdp.store.criteria.BaseCriteria;
import uz.pdp.store.enitity.base.BaseEntity;
import uz.pdp.store.specification.base.BaseSpecification;


public interface GenericSpecification<T extends BaseEntity, C extends BaseCriteria> extends BaseSpecification {

    Specification<T> getSpecification(C criteria);

}
