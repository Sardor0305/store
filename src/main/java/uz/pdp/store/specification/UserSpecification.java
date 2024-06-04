package uz.pdp.store.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import uz.pdp.store.criteria.UserCriteria;
import uz.pdp.store.enitity.User;
import uz.pdp.store.unitls.CommonUtils;


import java.util.Optional;



@Component
public class UserSpecification implements GenericSpecification<User, UserCriteria> {

    @Override
    public Specification<User> getSpecification(UserCriteria criteria) {
        return firstNameSpecification(criteria.getFirstName())
                .and(lastNameSpecification(criteria.getLastName()))
                .and(phoneNumberSpecification(criteria.getPhone()));

    }

//    private Specification<User> roleSpecification(UserRole role) {
//        return (root, criteriaQuery, criteriaBuilder) -> Optional.ofNullable(role)
//                .map(i -> criteriaBuilder.equal(root.get("role"), i))
//                .orElse(null);
//    }

    private Specification<User> phoneNumberSpecification(String phone) {
        return (root, criteriaQuery, criteriaBuilder) -> Optional.ofNullable(phone)
                .map(i -> criteriaBuilder.like(root.get("phone"), CommonUtils.likeFormat(i)))
                .orElse(null);
    }

    private Specification<User> lastNameSpecification(String lastName) {
        return (root, criteriaQuery, criteriaBuilder) -> Optional.ofNullable(lastName)
                .map(i -> criteriaBuilder.like(root.get("lastName"), CommonUtils.likeFormat(i)))
                .orElse(null);
    }

    private Specification<User> firstNameSpecification(String firstName) {
        return (root, criteriaQuery, criteriaBuilder) -> Optional.ofNullable(firstName)
                .map(i -> criteriaBuilder.like(root.get("firstName"), CommonUtils.likeFormat(i)))
                .orElse(null);
    }

}
