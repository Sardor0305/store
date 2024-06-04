package uz.pdp.store.service;


import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.store.criteria.UserCriteria;
import uz.pdp.store.dto.user.PasswordDTO;
import uz.pdp.store.dto.user.UserCreateDTO;
import uz.pdp.store.dto.user.UserDTO;
import uz.pdp.store.dto.user.UserUpdateDTO;
import uz.pdp.store.enitity.User;
import uz.pdp.store.exeptions.NotFoundException;
import uz.pdp.store.mapper.UserMapper;
import uz.pdp.store.repository.UserRepository;
import uz.pdp.store.service.base.AbstractService;
import uz.pdp.store.service.base.BaseService;
import uz.pdp.store.service.base.GenericService;
import uz.pdp.store.specification.UserSpecification;
import uz.pdp.store.unitls.MessageKey;
import uz.pdp.store.unitls.ThreadLocalSingleton;


import java.time.LocalDate;
import java.util.List;


@Service
public class UserService
        extends AbstractService<UserMapper, UserRepository>
        implements GenericService<User, UserDTO, UserCreateDTO, UserUpdateDTO, UserCriteria>, UserDetailsService, BaseService {

    private final PasswordEncoder passwordEncoder;
    private final UserSpecification specification;

    public UserService(
            UserMapper mapper,
            UserRepository repository, PasswordEncoder passwordEncoder,
//            @Lazy PasswordEncoder passwordEncoder,
            UserSpecification specification
    ) {
        super(mapper, repository);
        this.passwordEncoder = passwordEncoder;
        this.specification = specification;
    }

    @Override
    public UserDTO get(Long id) {
        return mapper.fromEntity(findById(id));
    }

    public UserDTO getSessionUser() {
        return mapper.fromEntity(ThreadLocalSingleton.getUser());
    }

    @Override
    public List<UserDTO> getAll(UserCriteria criteria) {
        return repository.findAll(specification.getSpecification(criteria))
                .stream()
                .map(mapper::fromEntity)
                .toList();
    }

    @Override
    public Page<UserDTO> getAll(UserCriteria criteria, Pageable pageable) {
        return repository.findAll(specification.getSpecification(criteria), pageable).map(mapper::fromEntity);
    }

    @Transactional
    @Override
    public UserDTO create(UserCreateDTO dto) {

        User user = mapper.toEntity(dto);
        repository.save(user);
        return mapper.fromEntity(user);
    }

    @Transactional
    @Override
    public UserDTO update(Long id, UserUpdateDTO dto) {

        User user = findById(id);
        mapper.toEntity(dto, user);
        repository.save(user);
        return mapper.fromEntity(user);
    }

    @SneakyThrows
    @Transactional
    public void changePassword(PasswordDTO passwordDto) {
        User user = ThreadLocalSingleton.getUser();
        if (!passwordEncoder.matches(passwordDto.getCurrentPassword(), user.getPassword())) {
            throw new BadRequestException(MessageKey.PASSWORD_DOES_NOT_MATCH);
        }
        if (!passwordDto.getNewPassword().equals(passwordDto.getConfirmPassword())) {
            throw new BadRequestException(MessageKey.INVALID_CONFIRMATION);
        }
        mapper.fromPassword(passwordDto.getNewPassword(), user);
        repository.save(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User user = mapper.toDeleteEntity(findById(id));
        repository.save(user);
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(MessageKey.USER_NOT_FOUND));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByPhone(username).orElseThrow(() -> new UsernameNotFoundException(MessageKey.BAD_CREDENTIALS));
    }












}