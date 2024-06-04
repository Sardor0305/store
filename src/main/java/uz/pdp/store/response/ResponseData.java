package uz.pdp.store.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseData<T> {
    private T data;
    private ResponseError error;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime time;

    private ResponseData() {
        this.time = LocalDateTime.now();
    }

    private ResponseData(T data) {
        this();
        this.data = data;
    }

    public ResponseData(ResponseError error) {
        this();
        this.error = error;
    }

    public static <T> ResponseEntity<ResponseData<T>> ok(T data) {
        return ResponseEntity.ok(new ResponseData<>(data));
    }

    public static <T> ResponseEntity<ResponseData<T>> response(T data, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ResponseData<>(data), httpStatus);
    }

    public static <T> ResponseEntity<ResponseData<T>> error(ResponseError error, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ResponseData<>(error), httpStatus);
    }

}
