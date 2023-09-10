package com.example.mydoctorapp.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final int TAX_NUMBER_LENGTH = 9;
    public static final int PHONE_NUMBER_LENGTH = 10;
    public static final int SOCIAL_SECURITY_NUMBER_LENGTH = 11;
    public static final String CITIZENS_TEMPLATE_VALUE = "citizens_view";
    public static final String CITIZENS = "citizens";
    public static final String MAIN_TEMPLATE_VALUE = "main_view";
    public static final String DOCTOR_TEMPLATE_VALUE = "doctor_view";
    public static final String SORT_ASCENDING = "asc";
    public static final String START_ITEM = "startItem";
    public static final String END_ITEM = "endItem";
    public static final int ONE_VALUE = 1;
    public static final String REGEX_EMAIL_FORMAT = "^[A-Za-z0-9+_.-]+@mydoctorapp\\.com$";
    public static final String SUCCESS_MESSAGE_ALERT = "successMessage";
    public static final String FAILURE_MESSAGE_ALERT = "failureMessage";
    public static final String GENERIC_ERROR_FOR_UI = "An error occurred, please contact the support team";
}
