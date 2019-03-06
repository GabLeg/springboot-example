package com.example.springbootexample.services;

import com.example.springbootexample.config.TestParent;
import com.example.springbootexample.domain.Brewery;
import com.example.springbootexample.domain.brewMaster.BrewMaster;
import com.example.springbootexample.repositories.BreweryRepository;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BreweryServiceTest extends TestParent {

    private static final Long EXISTING_ID = 1L;
    private static final Long UNKNOWN_ID = 2L;
    private static final Brewery A_BREWERY = mock(Brewery.class);

    private BreweryService breweryService;
    private BreweryRepository breweryRepositoryMock;

    @Before
    public void init() {
        breweryRepositoryMock = mock(BreweryRepository.class);
        breweryService = new BreweryService(breweryRepositoryMock);
    }

    @Test
    public void givenBreweryId_whenRetrieveBrewery_thenReturnBrewery() {
        when(breweryRepositoryMock.findById(EXISTING_ID)).thenReturn(Optional.of(A_BREWERY));

        Brewery brewery = breweryService.retrieveBrewery(EXISTING_ID);

        assertEquals(A_BREWERY, brewery);
    }

    @Test(expected = EntityNotFoundException.class)
    public void givenUnknownBreweryId_whenRetrieveBrewery_thenThowEntityNotFound() {
        when(breweryRepositoryMock.findById(UNKNOWN_ID)).thenReturn(Optional.empty());

        breweryService.retrieveBrewery(UNKNOWN_ID);
    }

    @Test
    public void givenBreweryIdAndBrewMaster_whenAddBrewMaster_thenBreweryRepositoryIsCalled() {
        when(breweryRepositoryMock.findById(EXISTING_ID)).thenReturn(Optional.of(A_BREWERY));
        BrewMaster brewMaster = new BrewMaster();

        breweryService.addBrewMaster(EXISTING_ID, brewMaster);

        verify(breweryRepositoryMock).save(A_BREWERY);
    }
}