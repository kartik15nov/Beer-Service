package com.ub.beerService.services.inventory;

import com.ub.beerService.services.inventory.feign.InventoryServiceFeignClient;
import com.ub.brewery.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Profile("local-discovery")
@Slf4j
@RequiredArgsConstructor
@Service
public class BeerInventoryServiceFeignImpl implements BeerInventoryService {

    private final InventoryServiceFeignClient inventoryServiceFeignClient;

    @Override
    public Integer getOnHandInventory(UUID beerId) {
        log.debug("Calling Inventory Service, BeerId: {}", beerId);

        ResponseEntity<List<BeerInventoryDto>> responseEntity = inventoryServiceFeignClient.getOnHandInventory(beerId);

        Integer qtyOnHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();

        log.debug("BeerId: {}, Qty On Hand: {}", beerId, qtyOnHand);

        return qtyOnHand;
    }
}
