package uz.pdp.store.controllers;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.store.controllers.base.AbstractController;
import uz.pdp.store.criteria.UserCriteria;
import uz.pdp.store.dto.user.PasswordDTO;
import uz.pdp.store.dto.user.UserCreateDTO;
import uz.pdp.store.dto.user.UserDTO;
import uz.pdp.store.dto.user.UserUpdateDTO;
import uz.pdp.store.enitity.UserRole;
import uz.pdp.store.response.ResponseData;
import uz.pdp.store.response.ResponseMessage;
import uz.pdp.store.service.UserService;
import uz.pdp.store.unitls.APIUtils;
import uz.pdp.store.unitls.MessageKey;

import java.util.List;


@RestController
@RequestMapping(value = APIUtils.API + APIUtils.V1 + APIUtils.USER)
public class UserController extends AbstractController<UserService> {

    public UserController(UserService service) {
        super(service);
    }

    @GetMapping(value = APIUtils.ID)
    public ResponseEntity<ResponseData<UserDTO>> get(@PathVariable(name = "id") Long id) {
        return ResponseData.ok(service.get(id));
    }

    @GetMapping("/current")
    public ResponseEntity<ResponseData<UserDTO>> getSessionUser() {
        return ResponseData.ok(service.getSessionUser());
    }

    @GetMapping(value = APIUtils.EXCEL)
    public ResponseEntity<ResponseData<List<UserDTO>>> getAll(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "phone", required = false) String phone
    ) {
        return ResponseData.ok(service.getAll(UserCriteria.builder()
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .build()));
    }


    @GetMapping(value = APIUtils.ALL)
    public ResponseEntity<ResponseData<Page<UserDTO>>> getAll(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "phone", required = false) String phone,
            Pageable pageable
    ) {
        return ResponseData.ok(service.getAll(UserCriteria.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .phone(phone)
                        .build(),
                pageable)
        );
    }

    @PostMapping
    public ResponseEntity<ResponseData<UserDTO>> create(@Valid @RequestBody UserCreateDTO dto) {
        return ResponseData.ok(service.create(dto));
    }


    @PutMapping(value = APIUtils.ID)
    public ResponseEntity<ResponseData<UserDTO>> update(@PathVariable(name = "id") Long id, @Valid @RequestBody UserUpdateDTO dto) {
        return ResponseData.ok(service.update(id, dto));
    }

    @PutMapping(APIUtils.CHANGE_PASSWORD)
    public ResponseEntity<ResponseData<ResponseMessage>> changePassword(@Valid @RequestBody PasswordDTO passwordDto) {
        service.changePassword(passwordDto);
        return responseBuilder.success(MessageKey.SUCCESS);
    }

    @DeleteMapping(value = APIUtils.ID)
    public ResponseEntity<ResponseData<ResponseMessage>> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return responseBuilder.success(MessageKey.SUCCESS);
    }

}
