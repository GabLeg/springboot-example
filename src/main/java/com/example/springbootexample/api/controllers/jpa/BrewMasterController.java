package com.example.springbootexample.api.controllers.jpa;

import com.example.springbootexample.api.controllers.dto.jpa.BrewMasterDto;
import com.example.springbootexample.domain.object.brewMaster.BrewMaster;
import com.example.springbootexample.infra.repository.BrewMasterRepository;
import com.example.springbootexample.mappers.ModelMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/brewMasters")
public class BrewMasterController {

  // Using Repository directly on purpose. When doing CRUD, there is no need of Service.
  private final BrewMasterRepository brewMasterRepository;
  private final ModelMapper mapper;

  @Autowired
  public BrewMasterController(ModelMapper mapper, BrewMasterRepository brewMasterRepository) {
    this.mapper = mapper;
    this.brewMasterRepository = brewMasterRepository;
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public BrewMasterDto getBrewMaster(@PathVariable("id") @NonNull Long id) {
    Optional<BrewMaster> optionalBrewMaster = brewMasterRepository.findById(id);
    if (optionalBrewMaster.isPresent()) {
      return mapper.map(optionalBrewMaster.get(), BrewMasterDto.class);
    }
    throw new EntityNotFoundException();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BrewMasterDto createBrewMaster(@RequestBody BrewMasterDto newBrewMaster) {
    BrewMaster brewMaster = mapper.map(newBrewMaster, BrewMaster.class);
    brewMaster = brewMasterRepository.save(brewMaster);
    return mapper.map(brewMaster, BrewMasterDto.class);
  }
}
