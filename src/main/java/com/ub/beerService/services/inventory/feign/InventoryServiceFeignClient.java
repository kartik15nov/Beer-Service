package com.ub.beerService.services.inventory.feign;

import com.ub.beerService.services.inventory.BeerInventoryServiceRestTemplateImpl;
import com.ub.brewery.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "inventory-service", fallback = InventoryFailoverService.class)
public interface InventoryServiceFeignClient {

    @GetMapping(BeerInventoryServiceRestTemplateImpl.INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(@PathVariable(value = "beerId") UUID beerId);
}
