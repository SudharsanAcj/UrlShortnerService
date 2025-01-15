package com.utills.urlShortner.service;

import com.utills.urlShortner.data.entity.UrlMaster;
import com.utills.urlShortner.data.repository.UrlMasterRepository;
import com.utills.urlShortner.mapper.EntityToResponseMapper;
import com.utills.urlShortner.mapper.RequestToEntityMapper;
import com.utills.urlShortner.models.GenerateShortUrlRequest;
import com.utills.urlShortner.models.GenerateShortUrlResponse;
import com.utills.urlShortner.models.GetCustomAliasDetailsResponse;
import com.utills.urlShortner.models.GetShortUrlDetailsResponse;
import com.utills.urlShortner.models.ModifyShortUrlRequest;
import com.utills.urlShortner.models.ModifyShortUrlResponse;
import com.utills.urlShortner.utils.ShortIdGenerator;
import com.utills.urlShortner.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ValidationException;

@Service
public class UrlShortnerServiceImpl implements UrlShortnerService {

    @Autowired
    private UrlMasterRepository urlMasterRepository;

    @Autowired
    private CounterService counterService;

    @Autowired
    private RequestToEntityMapper requestToEntityMapper;

    @Autowired
    private EntityToResponseMapper entityToResponseMapper;

    @Autowired
    private RequestValidator requestValidator;

    @Value("${urlshortener.domain.name}")
    private String domainName;

    @Value("${urlshortener.shortid.length}")
    private int shortIdLength;

    @Override
    public GenerateShortUrlResponse generateShortUrl(GenerateShortUrlRequest request) throws ValidationException {
        // Validate the request
        requestValidator.validateGenerateShortUrlRequest(request);

        // Convert expiryDate from DD/MM/YYYY to epoch time
        long expiryEpochTime = requestValidator.convertToEpochTime(request.getExpiryDate());

        // Fetch the next sequence value for the ID
        long nextId = counterService.getNextSequence("short_url_counter");

        // Map the request to the UrlMaster entity
        UrlMaster urlMaster = requestToEntityMapper.toUrlMaster(request);
        urlMaster.setId(nextId);
        urlMaster.setExpiryEpochtime((int) expiryEpochTime);

        // Generate short URL
        String shortUrl = ShortIdGenerator.generateShortUrl(request.getCustomAlaisName(), domainName, shortIdLength);
        urlMaster.setShortUrl(shortUrl);

        urlMasterRepository.save(urlMaster);

        GenerateShortUrlResponse response = entityToResponseMapper.toGenerateShortUrlResponse(urlMaster);
        response.setCustomAlaisUrl(shortUrl); // Set the customAlaisUrl field in the response
        return response;
    }

    @Override
    public ModifyShortUrlResponse modifyShortUrl(ModifyShortUrlRequest request) {
        UrlMaster urlMaster = urlMasterRepository.findByShortUrl(request.getShortUrl());
        if (urlMaster != null) {
            urlMaster = requestToEntityMapper.toUrlMaster(request);
            urlMasterRepository.save(urlMaster);
        }
        return entityToResponseMapper.toModifyShortUrlResponse(urlMaster);
    }

    @Override
    public GetShortUrlDetailsResponse getShortUrlDetails(String shortUrl) {
        UrlMaster urlMaster = urlMasterRepository.findByShortUrl(shortUrl);
        return entityToResponseMapper.toGetShortUrlDetailsResponse(urlMaster);
    }


    @Override
    public GetCustomAliasDetailsResponse getCustomAliasDetails(String customAlaisUrl) {
        UrlMaster urlMaster = urlMasterRepository.findByCustomAliasName(customAlaisUrl);
        return entityToResponseMapper.toGetCustomAliasDetailsResponse(urlMaster);
    }

    @Override
    public void redirectUrl(String shortUrl) {
        // Implement your logic here
        // Handle redirection
    }
}
