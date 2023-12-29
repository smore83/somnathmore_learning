package com.backend.user.utils;

public class Constants {
    public static final String PASSWORD_REGEX="^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{7,}$";
    public static final String PASSWORD_VALIDATION="Password should be correct";
    public static final String EMAIL_VALIDATION="Email should be correct";
    public static final String FIRSTNAME_VALIDATION="FirstName should be correct";
    public static final String LASTNAME_VALIDATION="LastName should be correct";
    public static final String TITLE_VALIDATION="Title should be correct";
    public static final String DESCRIPTION_VALIDATION="Description should be correct";
    public static final String FIRSTNAME_VALIDATION_STRENGTH="FirstName have atleast 4 characters";
    public static final String LASTNAME_VALIDATION_STRENGTH="LastName have atleast 4 characters ";
    public static final String TITLE_VALIDATION_STRENGTH="Title have atleast 4 characters";
    public static final String DESCRIPTION_VALIDATION_STRENGTH="Description should be length at least 4 characters";
    public static final String BASE_URL="/users";
}
