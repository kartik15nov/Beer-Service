package com.unknownbrain.msscbrewery.services;

import com.unknownbrain.msscbrewery.domain.Beer;
import com.unknownbrain.msscbrewery.repositories.BeerRepository;
import com.unknownbrain.msscbrewery.web.mapper.BeerMapper;
import com.unknownbrain.msscbrewery.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Log4j2
@Service
public class BeerServiceImpl implements BeerService {

    BeerRepository beerRepository;
    BeerMapper beerMapper;

    @Override
    public BeerDto getBeerById(UUID beerId) throws ChangeSetPersister.NotFoundException {
        return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) throws ChangeSetPersister.NotFoundException {
        Beer beer = beerRepository.findById(beerId).orElseThrow(ChangeSetPersister.NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }

    @Override
    public void deleteById(UUID beerId) {
        log.debug("Deleting a beer...");
        beerRepository.deleteById(beerId);
    }

}
