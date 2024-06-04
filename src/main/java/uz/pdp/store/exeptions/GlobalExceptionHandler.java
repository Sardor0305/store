package uz.pdp.store.exeptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.store.response.ResponseData;
import uz.pdp.store.response.ResponseError;
import uz.pdp.store.service.MessageService;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageService messageService;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseData<ResponseError>> handleNotFoundException(final NotFoundException ex, HttpServletRequest request) {
        return getResponseEntity(ex, request, HttpStatus.NOT_FOUND);
    }

  

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseData<ResponseError>> handleBadRequestException(final BadRequestException ex, HttpServletRequest request) {
        return getResponseEntity(ex, request, HttpStatus.BAD_REQUEST);
    }

    

//
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ResponseData<ResponseError>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .filter(Objects::nonNull)
                .map(messageService::getMessage)
                .distinct()
                .collect(Collectors.joining(", "));
        HttpStatus httpStatus = (HttpStatus) ex.getStatusCode();
        return ResponseData.error(ResponseError.response(errorMessage, httpStatus.getReasonPhrase()), httpStatus);
    }
    private <E extends Exception> ResponseEntity<ResponseData<ResponseError>> getResponseEntity(E ex, HttpServletRequest request, HttpStatus status) {
        return getResponseEntity(ex.getMessage(), request, status);
    }

    private ResponseEntity<ResponseData<ResponseError>> getResponseEntity(String message, HttpServletRequest request, HttpStatus status) {
        return ResponseData.error(
                ResponseError.response(message, status.getReasonPhrase(), request),
                status
        );
    }

}
