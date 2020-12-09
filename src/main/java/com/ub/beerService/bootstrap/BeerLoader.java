package com.ub.beerService.bootstrap;

import com.ub.beerService.domain.Beer;
import com.ub.beerService.repositories.BeerRepository;
import com.ub.beerService.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) throws Exception {

        if (beerRepository.count() == 0)
            loadBeerObjects();
    }

    private void loadBeerObjects() {
        Beer b1 = Beer.builder()
                .beerName("KingFisher")
                .beerStyle(BeerStyleEnum.IPA.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .upc(BEER_1_UPC)
                .price(new BigDecimal("12.95"))
                .build();

        Beer b2 = Beer.builder()
                .beerName("Hunter")
                .beerStyle(BeerStyleEnum.PALE_ALE.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .upc(BEER_2_UPC)
                .price(new BigDecimal("11.95"))
                .build();

        Beer b3 = Beer.builder()
                .beerName("Bira")
                .beerStyle(BeerStyleEnum.PALE_ALE.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .upc(BEER_3_UPC)
                .price(new BigDecimal("11.95"))
                .build();

        beerRepository.save(b1);
        beerRepository.save(b2);
        beerRepository.save(b3);

        log.info("Loaded Beers : " + beerRepository.count());
    }
}
