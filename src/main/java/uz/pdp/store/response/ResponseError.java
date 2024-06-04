package uz.pdp.store.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseError {

    private String message;
    private String reason;
    private String path;

    private ResponseError(@NonNull String message) {
        this.message = message;
    }

    private ResponseError(@NonNull String message, String reason) {
        this(message);
        this.reason = reason;
    }

    private ResponseError(@NonNull String message, String reason, @Nullable HttpServletRequest request) {
        this(message, reason);
        if (Objects.nonNull(request)) {
            this.path = request.getRequestURI();
        }
    }

    public static ResponseError response(@NonNull String message) {
        return response(message, null);
    }

    public static ResponseError response(@NonNull String message, String reason) {
        return response(message, reason, null);
    }

    public static ResponseError response(@NonNull String message, String reason, @Nullable HttpServletRequest request) {
        return new ResponseError(message, reason, request);
    }

}
