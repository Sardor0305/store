package uz.pdp.store.unitls;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageKey {
    public static final String MESSAGE_NOT_FOUND = "message.not.found";
    public static final String USER_NOT_FOUND = "user.not.found";

    public static final String INVALID_TOKEN = "INVALID.TOKEN";
    public static final String INVALID_PASSWORD = "INVALID.PASSWORD";
    public static final String INVALID_NEW_PASSWORD = "INVALID_NEW_PASSWORD";
    public static final String INVALID_CONFIRM_PASSWORD = "INVALID_CONFIRM_PASSWORD";
    public static final String INVALID_PHONE = "INVALID_PHONE";
    public static final String INVALID_FIRSTNAME = "INVALID_FIRSTNAME";
    public static final String INVALID_LASTNAME = "INVALID_LASTNAME";
    public static final String INVALID_LANGUAGE = "INVALID_LANGUAGE";
    public static final String INVALID_ROLE = "INVALID_ROLE";
    public static final String SUCCESS = "SUCCESS";
    public static final String DRUG_NOT_FOUND = "DRUG_NOT_FOUND";
    public static final String INVALID_REFRESH_TOKEN = "INVALID_REFRESH_TOKEN";
    public static final String TOKEN_EXPIRED = "TOKEN_EXPIRED";
    public static final String USERNAME_PASSWORD_INCORRECT = "USERNAME_PASSWORD_INCORRECT";
    public static final String PASSWORD_DOES_NOT_MATCH = "PASSWORD_DOES_NOT_MATCH";
    public static final String INVALID_CONFIRMATION = "INVALID_CONFIRMATION";
    public static final String BAD_CREDENTIALS = "BAD_CREDENTIALS";
}
