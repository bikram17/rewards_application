package com.Busybox.rewards.application.config;


import java.security.SecureRandom;

public class OTPGenerator {
    private static final int MIN_4_DIGIT_VALUE = 1000;
    private static final int MAX_4_DIGIT_VALUE = 9999;
    private static final int MIN_6_DIGIT_VALUE = 100000;
    private static final int MAX_6_DIGIT_VALUE = 999999;

    private static final String ALPHA_NUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static SecureRandom secureRandom = new SecureRandom();

    public static String generate4DigitCode() {
        int code = MIN_4_DIGIT_VALUE + secureRandom.nextInt(MAX_4_DIGIT_VALUE - MIN_4_DIGIT_VALUE + 1);
        return String.format("%04d", code);
    }

    public static String generate6DigitCode() {
        int code = MIN_6_DIGIT_VALUE + secureRandom.nextInt(MAX_6_DIGIT_VALUE - MIN_6_DIGIT_VALUE + 1);
        return String.format("%06d", code);
    }

    public static String generateAlphanumeric6DigitCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = secureRandom.nextInt(ALPHA_NUMERIC_CHARACTERS.length());
            code.append(ALPHA_NUMERIC_CHARACTERS.charAt(index));
        }
        return code.toString();
    }

    public static String generateAlphanumeric8DigitCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = secureRandom.nextInt(ALPHA_NUMERIC_CHARACTERS.length());
            code.append(ALPHA_NUMERIC_CHARACTERS.charAt(index));
        }
        return code.toString();
    }
}






