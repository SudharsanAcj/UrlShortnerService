package com.utills.urlShortner.mapper;

import com.utills.urlShortner.data.entity.UrlMaster;
import com.utills.urlShortner.models.GenerateShortUrlRequest;
import com.utills.urlShortner.models.ModifyShortUrlRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface RequestToEntityMapper {
    RequestToEntityMapper INSTANCE = Mappers.getMapper(RequestToEntityMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdEpochtime", expression = "java((int) (System.currentTimeMillis() / 1000L))")
    @Mapping(target = "modifiedEpochtime", expression = "java((int) (System.currentTimeMillis() / 1000L))")
    @Mapping(target = "modifiedBy", constant = "system")
    @Mapping(target = "createdBy", constant = "system")
    @Mapping(target = "status", constant = "true")
    @Mapping(target = "customAliasName", source = "customAlaisName")
    @Mapping(target = "expiryEpochtime", ignore = true) // We'll set this manually in the service
    UrlMaster toUrlMaster(GenerateShortUrlRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "modifiedEpochtime", expression = "java((int) (System.currentTimeMillis() / 1000L))")
    @Mapping(target = "modifiedBy", constant = "system")
    @Mapping(target = "customAliasName", source = "customAlaisName")
    @Mapping(target = "expiryEpochtime", ignore = true) // We'll set this manually in the service
    UrlMaster toUrlMaster(ModifyShortUrlRequest request);
}
