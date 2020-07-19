package com.unknownbrain.msscbrewery.bootstrap;

import com.unknownbrain.msscbrewery.domain.Beer;
import com.unknownbrain.msscbrewery.repositories.BeerRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Log4j2
@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .beerName("KingFisher")
                    .beerStyle("IPC")
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .upc(33010000001L)
                    .price(new BigDecimal("12.95"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Hunter")
                    .beerStyle("PALE_ALE")
                    .minOnHand(12)
                    .quantityToBrew(200)
                    .upc(33010000002L)
                    .price(new BigDecimal("11.95"))
                    .build());
        }

        log.info("Loaded Beers : " + beerRepository.count());
    }
}
