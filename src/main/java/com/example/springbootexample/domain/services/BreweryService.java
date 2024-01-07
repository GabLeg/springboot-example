package com.example.springbootexample.domain.services;

import com.example.springbootexample.domain.object.Beer;
import com.example.springbootexample.domain.object.Brewery;
import com.example.springbootexample.domain.object.brewMaster.BrewMaster;
import com.example.springbootexample.infra.repository.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class BreweryService {

  private final BreweryRepository breweryRepository;

  @Autowired
  public BreweryService(BreweryRepository breweryRepository) {
    this.breweryRepository = breweryRepository;
  }

  public Brewery retrieveBrewery(Long id) {
    Optional<Brewery> optionalBrewery = breweryRepository.findById(id);
    if (optionalBrewery.isPresent()) {
      return optionalBrewery.get();
    }
    throw new EntityNotFoundException();
  }

  public List<Brewery> retrieveAllBrewery() {
    return breweryRepository.findAll();
  }

  public Brewery createBrewery(Brewery brewery) {
    return breweryRepository.save(brewery);
  }

  public void addBrewMaster(Long breweryId, BrewMaster brewMaster) {
    Brewery brewery = retrieveBrewery(breweryId);
    brewery.getEmployees().add(brewMaster);
    breweryRepository.save(brewery);
  }

  public List<Beer> retrieveBeerList(Long id) {
    Brewery brewery = retrieveBrewery(id);
    return brewery.getBeerList();
  }

  public List<BrewMaster> retrieveEmployeeList(Long id) {
    Brewery brewery = retrieveBrewery(id);
    return brewery.getEmployees();
  }
}
