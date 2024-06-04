package uz.pdp.store.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.store.controllers.base.AbstractController;
import uz.pdp.store.dto.login.LoginDTO;
import uz.pdp.store.dto.login.RefreshTokenDTO;
import uz.pdp.store.dto.login.SessionDto;
import uz.pdp.store.response.ResponseData;
import uz.pdp.store.service.AuthService;
import uz.pdp.store.unitls.APIUtils;


@RestController
@RequestMapping(APIUtils.API + APIUtils.V1 + APIUtils.AUTH)
public class AuthController extends AbstractController<AuthService> {

    public AuthController(AuthService service) {
        super(service);
    }

    @PostMapping(APIUtils.LOGIN)
    public ResponseEntity<ResponseData<SessionDto>> login(@Valid @RequestBody LoginDTO dto) {
        return ResponseData.ok(service.login(dto));
    }

    @PostMapping(APIUtils.REFRESH_TOKEN)
    public ResponseEntity<ResponseData<SessionDto>> refreshToken(@Valid @RequestBody RefreshTokenDTO dto) {
        return ResponseData.ok(service.refreshToken(dto));
    }
}
