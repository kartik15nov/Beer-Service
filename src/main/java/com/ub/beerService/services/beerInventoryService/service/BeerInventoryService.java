package com.ub.beerService.services.beerInventoryService.service;

import java.util.UUID;

public interface BeerInventoryService {
    Integer getOnHandInventory(UUID beerId);
}
