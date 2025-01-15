package com.utills.urlShortner.service;

import com.utills.urlShortner.models.GenerateShortUrlRequest;
import com.utills.urlShortner.models.GenerateShortUrlResponse;
import com.utills.urlShortner.models.GetCustomAliasDetailsResponse;
import com.utills.urlShortner.models.GetShortUrlDetailsResponse;
import com.utills.urlShortner.models.ModifyShortUrlRequest;
import com.utills.urlShortner.models.ModifyShortUrlResponse;

public interface UrlShortnerService {
    GenerateShortUrlResponse generateShortUrl(GenerateShortUrlRequest generateShortUrlRequest);
    ModifyShortUrlResponse modifyShortUrl(ModifyShortUrlRequest modifyShortUrlRequest);
    GetShortUrlDetailsResponse getShortUrlDetails(String shortUrl);
    GetCustomAliasDetailsResponse getCustomAliasDetails(String customAlaisUrl);
    void redirectUrl(String shortUrl);
}