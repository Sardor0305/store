package uz.pdp.store.response;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import uz.pdp.store.service.MessageService;
import uz.pdp.store.unitls.MessageKey;


import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;



@Component("customResponseBuilder")
@RequiredArgsConstructor
public class ResponseBuilder {

    private final MessageService messageService;

    public ResponseEntity<ResponseData<ResponseMessage>> success(String message) {
        return prepareResponse(null, message, HttpStatus.OK);
    }

    public ResponseEntity<ResponseData<ResponseMessage>> unauthorized(final HttpServletRequest request) {
        return unauthorized(request, MessageKey.INVALID_TOKEN);
    }

    public ResponseEntity<ResponseData<ResponseMessage>> unauthorized(final HttpServletRequest request, final String message) {
        return prepareResponse(request, message, UNAUTHORIZED);
    }

    public ResponseEntity<ResponseData<ResponseMessage>> badRequest(final HttpServletRequest request, final String message) {
        return prepareResponse(request, message, BAD_REQUEST);
    }

    private ResponseEntity<ResponseData<ResponseMessage>> prepareResponse(
            final HttpServletRequest request,
            String message,
            HttpStatus httpStatus
    ) {
        message = messageService.getMessage(message);
        return isSuccessStatus(httpStatus)
                ? ResponseData.ok(new ResponseMessage(message))
                : ResponseData.error(ResponseError.response(message, httpStatus.getReasonPhrase(), request), httpStatus);
    }

    private boolean isSuccessStatus(HttpStatus status) {
        return status.value() >= 200 && status.value() < 300;
    }

}
