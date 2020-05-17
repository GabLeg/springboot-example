package com.example.springbootexample.controllers;

import com.example.springbootexample.controllers.dto.BeerDTO;
import com.example.springbootexample.controllers.dto.BrewMasterDTO;
import com.example.springbootexample.controllers.dto.BreweryDTO;
import com.example.springbootexample.domain.Brewery;
import com.example.springbootexample.domain.brewMaster.BrewMaster;
import com.example.springbootexample.mappers.ModelMapper;
import com.example.springbootexample.services.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/breweries")
public class BreweryController {

    private final BreweryService breweryService;
    private final ModelMapper mapper;

    @Autowired
    public BreweryController(BreweryService breweryService, ModelMapper mapper) {
        this.breweryService = breweryService;
        this.mapper = mapper;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public BreweryDTO getBrewery(@PathVariable("id") @NonNull Long id) {
        return mapper.map(breweryService.retrieveBrewery(id), BreweryDTO.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BreweryDTO> getBreweries() {
        return mapper.mapList(breweryService.retrieveAllBrewery(), BreweryDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BreweryDTO createBrewery(@RequestBody BreweryDTO newBrewery) {
        Brewery brewery = mapper.map(newBrewery, Brewery.class);
        brewery = breweryService.createBrewery(brewery);
        return mapper.map(brewery, BreweryDTO.class);
    }

    @PutMapping("{id}/brewMasters")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addBrewMaster(@PathVariable("id") @NonNull Long id, @RequestBody BrewMasterDTO brewMasterDTO) {
        BrewMaster brewMaster = mapper.map(brewMasterDTO, BrewMaster.class);
        breweryService.addBrewMaster(id, brewMaster);
    }

    @GetMapping("{id}/brewMasters")
    @ResponseStatus(HttpStatus.OK)
    public List<BrewMasterDTO> getEmployees(@PathVariable("id") @NonNull Long id) {
        return mapper.mapList(breweryService.retrieveEmployeeList(id), BrewMasterDTO.class);
    }

    @GetMapping("{id}/beers")
    @ResponseStatus(HttpStatus.OK)
    public List<BeerDTO> getBeers(@PathVariable("id") @NonNull Long id) {
        return mapper.mapList(breweryService.retrieveBeerList(id), BeerDTO.class);
    }
}
