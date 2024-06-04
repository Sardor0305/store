package uz.pdp.store.constants;

import lombok.experimental.UtilityClass;

import java.util.Set;

import static uz.pdp.store.unitls.APIUtils.API;
import static uz.pdp.store.unitls.APIUtils.AUTH;
import static uz.pdp.store.unitls.APIUtils.LOGIN;
import static uz.pdp.store.unitls.APIUtils.REFRESH_TOKEN;
import static uz.pdp.store.unitls.APIUtils.V1;


@UtilityClass
public class Methods {

    public static final Set<String> WHITE_LIST_METHODS = Set.of(
            API + V1 + AUTH + "/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/v3/api-docs"
    );

    public static final Set<String> NON_FILTER_METHODS = Set.of(
            API + V1 + AUTH + LOGIN,
            API + V1 + AUTH + REFRESH_TOKEN,
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/v3/api-docs"
    );

}
