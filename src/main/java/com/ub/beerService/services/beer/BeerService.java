package com.ub.beerService.services.beer;

import com.ub.beerService.web.model.BeerDto;
import com.ub.beerService.web.model.BeerPagedList;
import com.ub.beerService.web.model.BeerStyleEnum;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {

    BeerPagedList<BeerDto> listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, boolean showInventoryOnHand);

    BeerDto getBeerById(UUID beerId, boolean showInventoryOnHand) throws ChangeSetPersister.NotFoundException;

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto) throws ChangeSetPersister.NotFoundException;

    void deleteById(UUID beerId);

    BeerDto getBeerByUpc(String upc);
}
