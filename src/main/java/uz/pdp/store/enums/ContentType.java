package uz.pdp.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;


@Getter
@AllArgsConstructor
public enum ContentType {
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    UNKNOWN("unknown");

    private final String value;

    public static ContentType getContentTypeByValue(String value) {
        return Arrays.stream(values())
                .filter(contentType -> contentType.getValue().equals(value))
                .findFirst()
                .orElse(ContentType.UNKNOWN);
    }

}
