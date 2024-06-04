package uz.pdp.store.enitity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.store.enitity.base.Auditable;
import uz.pdp.store.enums.Language;


import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction(value = "deleted = false")
@Table(
        name = "users",
        schema = "public",
        uniqueConstraints = @UniqueConstraint(columnNames = {"phone", "deleted"})
)
public class User extends Auditable implements UserDetails {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "language", nullable = false)
    private Language language = Language.RU;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;



//    @Enumerated(value = EnumType.STRING)
//    @Column(name = "role", nullable = false)
//    private UserRole role = UserRole.UNKNOWN;


    @Column(name = "last_active_time")
    private LocalDate lastActiveTime;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return null;
    }

    @Override
    public String getUsername() {
        return this.phone;
    }

}
