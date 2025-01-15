package com.utills.urlShortner.validator;

import com.utills.urlShortner.models.GenerateShortUrlRequest;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@Component
public class RequestValidator {

    private static final Pattern URL_PATTERN = Pattern.compile(
            "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\|www\\.)[a-zA-Z0-9_\\-]+(\\.[a-zA-Z]{2,5})+(\\.[a-zA-Z]{2,5})?([\\/\\w\\.-]*)*\\/?$");

    public void validateGenerateShortUrlRequest(GenerateShortUrlRequest request) throws ValidationException {
        if (request.getLongUrl() == null || request.getLongUrl().isEmpty() || !isValidUrl(request.getLongUrl())) {
            throw new ValidationException("Invalid long URL.");
        }
        if (request.getClientId() == null || request.getClientId().isEmpty()) {
            throw new ValidationException("Client ID should not be empty.");
        }
        if (request.getExpiryDate() != null && !request.getExpiryDate().isEmpty()) {
            long expiryEpochTime = convertToEpochTime(request.getExpiryDate());
            if (expiryEpochTime < System.currentTimeMillis() / 1000) {
                throw new ValidationException("Expiry date cannot be in the past.");
            }
            request.setExpiryDate(String.valueOf(expiryEpochTime));
        }

        // Validate custom alias name
        if (request.getCustomAlaisName() != null && !request.getCustomAlaisName().isEmpty()) {
            if (request.getCustomAlaisName().length() < 10) {
                throw new ValidationException("Custom alias name must be at least 10 characters long.");
            }
        }
    }

    private boolean isValidUrl(String url) {
        return URL_PATTERN.matcher(url).matches();
    }

    public long convertToEpochTime(String date) throws ValidationException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date parsedDate = sdf.parse(date);
            return parsedDate.getTime() / 1000;
        } catch (ParseException e) {
            throw new ValidationException("Invalid date format. Use DD/MM/YYYY.");
        }
    }

    public String convertToFormattedDate(long epochTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date(epochTime * 1000));
    }
}
