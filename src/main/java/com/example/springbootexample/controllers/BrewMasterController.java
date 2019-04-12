package com.example.springbootexample.controllers;

import com.example.springbootexample.controllers.dto.BrewMasterDTO;
import com.example.springbootexample.domain.brewMaster.BrewMaster;
import com.example.springbootexample.mappers.ModelMapper;
import com.example.springbootexample.repositories.BrewMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
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
    public BrewMasterDTO getBrewMaster(@PathVariable("id") @NotNull Long id) {
        Optional<BrewMaster> optionalBrewMaster = brewMasterRepository.findById(id);
        if (optionalBrewMaster.isPresent()) {
            return mapper.map(optionalBrewMaster.get(), BrewMasterDTO.class);
        }
        throw new EntityNotFoundException();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BrewMasterDTO createBrewMaster(@RequestBody BrewMasterDTO newBrewMaster) {
        BrewMaster brewMaster = mapper.map(newBrewMaster, BrewMaster.class);
        brewMaster = brewMasterRepository.save(brewMaster);
        return mapper.map(brewMaster, BrewMasterDTO.class);
    }
}
