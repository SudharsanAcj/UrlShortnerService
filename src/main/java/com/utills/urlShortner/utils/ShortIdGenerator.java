package com.utills.urlShortner.utils;


import javax.validation.ValidationException;
import java.util.Random;

public class ShortIdGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();

    public static String generateShortId(int length) {
        StringBuilder shortId = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            shortId.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return shortId.toString();
    }

    public static String generateShortUrl(String customAlaisName, String domainName, int shortIdLength) {
        if (customAlaisName != null && !customAlaisName.isEmpty()) {
            if (customAlaisName.length() < 10) {
                throw new ValidationException("Custom alias name must be at least 10 characters long.");
            }
            return domainName + "/" + customAlaisName;
        } else {
            return domainName + "/" + generateShortId(shortIdLength);
        }
    }
}
