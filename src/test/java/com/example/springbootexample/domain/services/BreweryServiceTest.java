package com.example.springbootexample.domain.services;

import com.example.springbootexample.config.TestParent;
import com.example.springbootexample.domain.object.Brewery;
import com.example.springbootexample.domain.object.brewMaster.BrewMaster;
import com.example.springbootexample.domain.services.BreweryService;
import com.example.springbootexample.infra.repository.BreweryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BreweryServiceTest extends TestParent {

  private static final Long EXISTING_ID = 1L;
  private static final Long UNKNOWN_ID = 2L;
  private static final Brewery A_BREWERY = mock(Brewery.class);

  @Mock
  private BreweryRepository breweryRepositoryMock;

  private BreweryService breweryService;

  @BeforeEach
  void init() {
    breweryService = new BreweryService(breweryRepositoryMock);
  }

  @Test
  void givenBreweryId_whenRetrieveBrewery_thenReturnBrewery() {
    when(breweryRepositoryMock.findById(EXISTING_ID)).thenReturn(Optional.of(A_BREWERY));

    Brewery brewery = breweryService.retrieveBrewery(EXISTING_ID);

    assertEquals(A_BREWERY, brewery);
  }

  @Test
  void givenUnknownBreweryId_whenRetrieveBrewery_thenThowEntityNotFound() {
    when(breweryRepositoryMock.findById(UNKNOWN_ID)).thenReturn(Optional.empty());

    Assertions.assertThrows(EntityNotFoundException.class, () -> breweryService.retrieveBrewery(UNKNOWN_ID));
  }

  @Test
  void givenBreweryIdAndBrewMaster_whenAddBrewMaster_thenBreweryRepositoryIsCalled() {
    when(breweryRepositoryMock.findById(EXISTING_ID)).thenReturn(Optional.of(A_BREWERY));
    BrewMaster brewMaster = new BrewMaster();

    breweryService.addBrewMaster(EXISTING_ID, brewMaster);

    verify(breweryRepositoryMock).save(A_BREWERY);
  }
}
