package com.ub.beerService.services.brewing;

import com.ub.beerService.config.JMSConfig;
import com.ub.beerService.domain.Beer;
import com.ub.beerService.events.BrewBeerEvent;
import com.ub.beerService.repositories.BeerRepository;
import com.ub.beerService.services.beerInventoryService.service.BeerInventoryService;
import com.ub.beerService.web.mapper.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BrewingService {
    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {
        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnHandInventory(beer.getId());

            log.debug("Min on hand: {}", beer.getMinOnHand());
            log.debug("Inventory on hand: {}", invQOH);

            if (beer.getMinOnHand() >= invQOH) {
                jmsTemplate.convertAndSend(JMSConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}
