package uz.pdp.store.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import uz.pdp.store.criteria.ProductCriteria;
import uz.pdp.store.enitity.Product;
import uz.pdp.store.unitls.CommonUtils;

import java.util.Optional;


@Component
public class ProductSpecification implements GenericSpecification<Product, ProductCriteria> {

    @Override
    public Specification<Product> getSpecification(ProductCriteria criteria) {
        return countrySpecification(criteria.getCountryName())
                .and(nameSpecification(criteria.getName()));
    }


    private Specification<Product> countrySpecification(String countryName) {
        return (root, criteriaQuery, criteriaBuilder) -> Optional.ofNullable(countryName)
                .map(i -> criteriaBuilder.like(root.get("countryName"), CommonUtils.toLikePattern(i)))
                .orElse(null);
    }

    private Specification<Product> nameSpecification(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> Optional.ofNullable(name)
                .map(i -> {
                            Predicate nameEnPredicate = criteriaBuilder.like(root.get("name"), CommonUtils.toLikePattern(i));
                            return criteriaBuilder.or(null);
                        }
                )
                .orElse(null);
    }



}
