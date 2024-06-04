package uz.pdp.store.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageSource messageSource;


    public String getMessage(@NonNull String key) {
        return getMessage(key, LocaleContextHolder.getLocale());
    }

    public String getMessage(@NonNull String key, Object... args) {
        return getMessage(key, LocaleContextHolder.getLocale(), args);
    }

    public String getMessage(String code, Locale locale, Object... args) {
        return messageSource.getMessage(code, args, locale);
    }

}
