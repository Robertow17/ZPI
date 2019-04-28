package com.zpi.loginForm.infrastructure;

public class FormValidator {
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    public static boolean isValidEmail(String email) {
        if(email == null || email.isEmpty()) return false;

        return email.matches(EMAIL_REGEX);
    }

    public static boolean isValidPassword(String password) {
        if (password == null || password.isEmpty()) return false;

        return hasPasswordProperLength(password) && doesPasswordContainNumber(password);
    }

    private static boolean hasPasswordProperLength(String password) {
        return password.length() >= MIN_PASSWORD_LENGTH;
    }

    private static boolean doesPasswordContainNumber(String password) {
        return password.matches(".*\\d.*");
    }
}
