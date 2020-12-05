package com.ub.beerService.events;

import com.ub.beerService.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent {
    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
