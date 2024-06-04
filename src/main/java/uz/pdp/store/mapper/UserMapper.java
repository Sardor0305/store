package uz.pdp.store.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.pdp.store.dto.user.UserCreateDTO;
import uz.pdp.store.dto.user.UserDTO;
import uz.pdp.store.dto.user.UserUpdateDTO;
import uz.pdp.store.enitity.User;
import uz.pdp.store.mapper.base.GenericMapper;


@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
@Component
public abstract class UserMapper
        implements GenericMapper<User, UserDTO, UserCreateDTO, UserUpdateDTO> {

//    protected PasswordEncoder passwordEncoder;

//    @Autowired
//    public void setPasswordEncoder(@Lazy PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    @Override
    @Mapping(target = "deleted", expression = "java(true)")
    @Mapping(target = "authorities", ignore = true)
    public abstract User toDeleteEntity(User entity);


    @Override
//    @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.getPassword()))")
    public abstract User toEntity(UserCreateDTO dto);

    @Override
    @Mapping(target = "id", ignore = true)
    public abstract void toEntity(UserUpdateDTO dto, @MappingTarget User entity);

    //    @Mapping(target = "password", expression = "java(passwordEncoder.encode(newPassword))")
    public abstract void fromPassword(String newPassword, @MappingTarget User user);

}
