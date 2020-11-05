package com.unknownbrain.beerservice.web.mapper;

import com.unknownbrain.beerservice.domain.Beer;
import com.unknownbrain.beerservice.web.model.BeerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    @Mapping(source = "minOnHand", target = "quantityOnHand")
    BeerDto beerToBeerDto(Beer beer);

    @Mappings(value = {@Mapping(source = "quantityOnHand", target = "minOnHand"), @Mapping(target = "quantityToBrew", ignore = true)})
    Beer beerDtoToBeer(BeerDto beerDto);
}
