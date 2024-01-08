package com.example.springbootexample.api.controllers.jpa;

import com.example.springbootexample.api.controllers.dto.jpa.BeerDto;
import com.example.springbootexample.api.controllers.dto.jpa.BrewMasterDto;
import com.example.springbootexample.api.controllers.dto.jpa.BreweryDto;
import com.example.springbootexample.domain.object.Brewery;
import com.example.springbootexample.domain.object.brewMaster.BrewMaster;
import com.example.springbootexample.domain.services.BreweryService;
import com.example.springbootexample.mappers.ModelMapper;
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
  public BreweryDto getBrewery(@PathVariable("id") @NonNull Long id) {
    return mapper.map(breweryService.retrieveBrewery(id), BreweryDto.class);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<BreweryDto> getBreweries() {
    return mapper.mapList(breweryService.retrieveAllBrewery(), BreweryDto.class);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BreweryDto createBrewery(@RequestBody BreweryDto newBrewery) {
    Brewery brewery = mapper.map(newBrewery, Brewery.class);
    brewery = breweryService.createBrewery(brewery);
    return mapper.map(brewery, BreweryDto.class);
  }

  @PutMapping("{id}/brewMasters")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void addBrewMaster(@PathVariable("id") @NonNull Long id, @RequestBody BrewMasterDto brewMasterDTO) {
    BrewMaster brewMaster = mapper.map(brewMasterDTO, BrewMaster.class);
    breweryService.addBrewMaster(id, brewMaster);
  }

  @GetMapping("{id}/brewMasters")
  @ResponseStatus(HttpStatus.OK)
  public List<BrewMasterDto> getEmployees(@PathVariable("id") @NonNull Long id) {
    return mapper.mapList(breweryService.retrieveEmployeeList(id), BrewMasterDto.class);
  }

  @GetMapping("{id}/beers")
  @ResponseStatus(HttpStatus.OK)
  public List<BeerDto> getBeers(@PathVariable("id") @NonNull Long id) {
    return mapper.mapList(breweryService.retrieveBeerList(id), BeerDto.class);
  }
}
