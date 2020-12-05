package com.ub.beerService.events;

import com.ub.beerService.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Builder
@RequiredArgsConstructor
@Data
public class BeerEvent implements Serializable {
    private static final long serialVersionUID = -1968701294745972508L;

    private final BeerDto beerDto;
}
