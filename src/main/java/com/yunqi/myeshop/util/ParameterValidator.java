package com.yunqi.myeshop.util;

import java.time.LocalDate;
import java.util.regex.Pattern;

public final class ParameterValidator {

    private ParameterValidator() {}

    public static boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pattern.matcher(email).matches();
    }
    public static boolean isPhoneNumberValid(String phoneNumber) {
        String phoneRegex = "^1[3-9]\\d{9}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        if (phoneNumber == null) {
            return false;
        }
        return pattern.matcher(phoneNumber).matches();
    }
    public static boolean isIdNumberValid(String id_number) {
        if(id_number==null||id_number.length()!=18)
            return false;
        String first17=id_number.substring(0,17);
        if(!first17.matches("\\d+"))
            return false;
        char lastChar=id_number.charAt(17);
        if(!(Character.isDigit(lastChar)||lastChar=='X'||lastChar=='x'))
            return false;
        String checkCode = calculateCheckCode(first17);
        char expectedLastChar = checkCode.charAt(0);
        return expectedLastChar == lastChar || (expectedLastChar == 'X' && (lastChar == 'X' || lastChar == 'x'));
    }
    public static boolean isPasswordValid(String password) {
        String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{6,18}$";
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        if (password == null)
            return false;
        return pattern.matcher(password).matches();
    }
    public static String calculateCheckCode(String first17) {
        int[] weights = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        char[] checkCodes = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        int sum = 0;
        for (int i = 0; i < first17.length(); i++) {
            sum += (first17.charAt(i) - '0') * weights[i];
        }
        int mod = sum % 11;
        return String.valueOf(checkCodes[mod]);
    }
    public static boolean isBirthdayValid(LocalDate birthday) {
        LocalDate past = LocalDate.of(1920, 1, 1);
        LocalDate today = LocalDate.now();
        return birthday.isBefore(today)&&birthday.isAfter(past);
    }
}
