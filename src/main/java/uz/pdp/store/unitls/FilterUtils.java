package uz.pdp.store.unitls;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import uz.pdp.store.response.ResponseData;
import uz.pdp.store.response.ResponseMessage;

import java.io.IOException;
import java.util.Objects;


@UtilityClass
public class FilterUtils {

    public static void errorResponse(HttpServletResponse response, ResponseEntity<ResponseData<ResponseMessage>> responseData) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(responseData.getStatusCode().value());

    }

    public static String getTokenFromRequest(HttpServletRequest request) {
        return Objects.requireNonNullElse(request.getHeader("Authorization"), "");
    }

}
