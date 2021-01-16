package com.ub.beerService.services.inventory.feign;

import com.ub.brewery.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "inventory-failover")
public interface InventoryFailoverFeignClient {

    @GetMapping("/inventory-failover")
    List<BeerInventoryDto> getOnHandInventory();
}
