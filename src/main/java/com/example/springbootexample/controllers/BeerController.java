package com.example.springbootexample.controllers;

import com.example.springbootexample.controllers.dto.BeerDTO;
import com.example.springbootexample.domain.Beer;
import com.example.springbootexample.mappers.ModelMapper;
import com.example.springbootexample.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/beers")
public class BeerController {

    private final BeerRepository beerRepository;
    private final ModelMapper mapper;

    @Autowired
    public BeerController(BeerRepository beerRepository, ModelMapper modelMapper) {
        this.beerRepository = beerRepository;
        this.mapper = modelMapper;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public BeerDTO getBeer(@PathVariable("id") @NotNull Long id) {
        Optional<Beer> optionalBeer = beerRepository.findById(id);
        if (optionalBeer.isPresent()) {
            return mapper.map(optionalBeer.get(), BeerDTO.class);
        }
        throw new EntityNotFoundException();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeerDTO createBeer(@RequestBody BeerDTO newBeer) {
        Beer beer = mapper.map(newBeer, Beer.class);
        beer = beerRepository.save(beer);
        return mapper.map(beer, BeerDTO.class);
    }
}
