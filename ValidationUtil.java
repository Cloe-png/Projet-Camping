package com.example.tp_camping.util;

import java.util.regex.Pattern;

public class ValidationUtil {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String USERNAME_REGEX = "^[A-Za-zÀ-ÿ '-]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,}$";
    private static final String FIRSTNAME_REGEX = "^[A-Za-zÀ-ÿ '-]+$";
    private static final String NAME_REGEX = "^[A-Za-zÀ-ÿ '-]+$";

    public static boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isValidUsername(String username) {
        return Pattern.matches(USERNAME_REGEX, username);
    }

    public static boolean isValidPassword(String password) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }

    public static boolean isValidName(String prenom, String nom) {
        return Pattern.matches(FIRSTNAME_REGEX, prenom) && Pattern.matches(NAME_REGEX, nom);
    }
}
