package uz.pdp.store.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;


@Getter
@RequiredArgsConstructor
public enum Language {
    UZ("uz"),
    RU("ru"),
    EN("en");
    private final String value;
    public static Language fromValue(String value) {
        return Arrays.stream(values())
                .filter(language -> language.value.equalsIgnoreCase(value))
                .findFirst()
                .orElse(Language.RU);
    }

}
