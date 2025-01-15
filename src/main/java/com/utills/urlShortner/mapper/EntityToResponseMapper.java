package com.utills.urlShortner.mapper;

import com.utills.urlShortner.data.entity.UrlMaster;
import com.utills.urlShortner.models.GenerateShortUrlResponse;
import com.utills.urlShortner.models.GetCustomAliasDetailsResponse;
import com.utills.urlShortner.models.GetShortUrlDetailsResponse;
import com.utills.urlShortner.models.ModifyShortUrlResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EntityToResponseMapper {
    EntityToResponseMapper INSTANCE = Mappers.getMapper(EntityToResponseMapper.class);

    @Mapping(target = "customAlaisUrl", source = "customAliasName")
    GenerateShortUrlResponse toGenerateShortUrlResponse(UrlMaster urlMaster);

    @Mapping(target = "customAlaisUrl", source = "customAliasName")
    ModifyShortUrlResponse toModifyShortUrlResponse(UrlMaster urlMaster);

    @Mapping(target = "customAlaisName", source = "customAliasName")
    @Mapping(target = "createdDate", expression = "java(new java.text.SimpleDateFormat(\"dd/MM/yyyy\").format(new java.util.Date(urlMaster.getCreatedEpochtime() * 1000L)))")
    @Mapping(target = "expiryDate", expression = "java(new java.text.SimpleDateFormat(\"dd/MM/yyyy\").format(new java.util.Date(urlMaster.getExpiryEpochtime() * 1000L)))")
    GetShortUrlDetailsResponse toGetShortUrlDetailsResponse(UrlMaster urlMaster);

    @Mapping(target = "customAlaisName", source = "customAliasName")
    @Mapping(target = "createdDate", expression = "java(new java.text.SimpleDateFormat(\"dd/MM/yyyy\").format(new java.util.Date(urlMaster.getCreatedEpochtime() * 1000L)))")
    @Mapping(target = "expiryDate", expression = "java(new java.text.SimpleDateFormat(\"dd/MM/yyyy\").format(new java.util.Date(urlMaster.getExpiryEpochtime() * 1000L)))")
    GetCustomAliasDetailsResponse toGetCustomAliasDetailsResponse(UrlMaster urlMaster);
}
