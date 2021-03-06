package com.ub.beerService.services.order;

import com.ub.beerService.repositories.BeerRepository;
import com.ub.brewery.model.BeerOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidator {

    private final BeerRepository beerRepository;

    public Boolean validateOrder(BeerOrderDto beerOrderDto) {
        AtomicInteger beersNotFound = new AtomicInteger();

        beerOrderDto.getBeerOrderLines()
                .forEach(beerOrderLineDto -> {
                    if (beerRepository.findByUpc(beerOrderLineDto.getUpc()) == null)
                        beersNotFound.incrementAndGet();
                });

        return beersNotFound.get() == 0;
    }
}
