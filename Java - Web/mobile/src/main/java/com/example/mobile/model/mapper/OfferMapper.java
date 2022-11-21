package com.example.mobile.model.mapper;

import com.example.mobile.model.dto.AddOfferDTO;
import com.example.mobile.model.dto.OfferDetailDTO;
import com.example.mobile.model.entity.OfferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    OfferEntity addOfferDtoToOfferEntity(AddOfferDTO addOfferDTO);

    AddOfferDTO offerEntityToCreateOrUpdateOfferDtoTo(OfferEntity offerEntity);

    @Mapping(source = "model.name", target = "model")
    @Mapping(source = "model.brand.name", target = "brand")
    @Mapping(source = "seller.firstName", target = "sellerFirstName")
    @Mapping(source = "seller.lastName", target = "sellerLastName")
    OfferDetailDTO offerEntityToOfferDetailDto(OfferEntity offerEntity);
     

}
