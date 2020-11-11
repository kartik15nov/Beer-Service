package com.ub.beerservice.web.controller;

import com.ub.beerservice.services.BeerService;
import com.ub.beerservice.web.model.BeerDto;
import com.ub.beerservice.web.model.BeerPagedList;
import com.ub.beerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerService beerService;

    @GetMapping(produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public BeerPagedList listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                   @RequestParam(value = "beerName", required = false) String beerName,
                                   @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
                                   @RequestParam(value = "showInventoryOnHand", required = false) boolean showInventoryOnHand) {

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        return beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);
    }

    @GetMapping("/{beerId}")
    @ResponseStatus(HttpStatus.OK)
    public BeerDto getBeerById(@PathVariable UUID beerId, @RequestParam(value = "showInventoryOnHand", required = false) boolean showInventoryOnHand) throws ChangeSetPersister.NotFoundException {
        return beerService.getBeerById(beerId, showInventoryOnHand); //TODO
    }

    @PostMapping
    public ResponseEntity<Void> saveNewBeer(@RequestBody @Validated BeerDto beerDto) {
        log.debug("Inside saveNewBeer..");

        val savedBeerDto = beerService.saveNewBeer(beerDto);

        val httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/beer/" + savedBeerDto.getId().toString());

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BeerDto updateBeerById(@PathVariable UUID beerId, @RequestBody @Validated BeerDto beerDto) throws ChangeSetPersister.NotFoundException {
        return beerService.updateBeer(beerId, beerDto);
    }

    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId) {
        beerService.deleteById(beerId);
    }

}
