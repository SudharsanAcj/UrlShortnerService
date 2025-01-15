package com.utills.urlShortner.controller;


import com.utills.urlShortner.UrlShortnerApi;
import com.utills.urlShortner.service.UrlShortnerService;
import com.utills.urlShortner.models.GenerateShortUrlRequest;
import com.utills.urlShortner.models.GenerateShortUrlResponse;
import com.utills.urlShortner.models.GetCustomAliasDetailsResponse;
import com.utills.urlShortner.models.GetShortUrlDetailsResponse;
import com.utills.urlShortner.models.ModifyShortUrlRequest;
import com.utills.urlShortner.models.ModifyShortUrlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortnerController implements UrlShortnerApi {

    @Autowired
    private UrlShortnerService urlShortnerService;

    @PostMapping("/generate-short-url")
    @Override
    public ResponseEntity<GenerateShortUrlResponse> generateShortUrl(GenerateShortUrlRequest generateShortUrlRequest) {
        GenerateShortUrlResponse response = urlShortnerService.generateShortUrl(generateShortUrlRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ModifyShortUrlResponse> modifyShortUrl(ModifyShortUrlRequest modifyShortUrlRequest) {
        ModifyShortUrlResponse response = urlShortnerService.modifyShortUrl(modifyShortUrlRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GetShortUrlDetailsResponse> getShortUrlDetails(String shortUrl) {
        GetShortUrlDetailsResponse response = urlShortnerService.getShortUrlDetails(shortUrl);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GetCustomAliasDetailsResponse> getCustomAliasDetails(String customAlaisUrl) {
        GetCustomAliasDetailsResponse response = urlShortnerService.getCustomAliasDetails(customAlaisUrl);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> redirectUrl(String shortUrl) {
        urlShortnerService.redirectUrl(shortUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "originalUrl"); // replace with the actual original URL
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
