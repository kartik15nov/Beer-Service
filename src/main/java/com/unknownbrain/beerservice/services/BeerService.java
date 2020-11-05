package com.unknownbrain.beerservice.services;

import com.unknownbrain.beerservice.web.model.BeerDto;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId) throws ChangeSetPersister.NotFoundException;

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto) throws ChangeSetPersister.NotFoundException;

    void deleteById(UUID beerId);
}
