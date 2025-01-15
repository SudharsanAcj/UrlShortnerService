package com.utills.urlShortner.data.repository;

import com.utills.urlShortner.data.entity.UrlMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlMasterRepository extends MongoRepository<UrlMaster, Long> {
    UrlMaster findByShortUrl(String shortUrl);
    UrlMaster findByCustomAliasName(String customAliasName);
}
