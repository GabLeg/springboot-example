package com.example.springbootexample.api.controllers;

import com.example.springbootexample.api.controllers.dto.GameDto;
import com.example.springbootexample.infra.mongo.GameDocument;
import com.example.springbootexample.infra.mongo.GameRepository;
import com.example.springbootexample.mappers.ModelMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/games")
public class MongoGameController {

  private final GameRepository gameRepository;
  private final ModelMapper mapper;

  @Autowired
  public MongoGameController(GameRepository gameRepository, ModelMapper modelMapper) {
    this.gameRepository = gameRepository;
    this.mapper = modelMapper;
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public GameDto getGameById(@PathVariable("id") @NonNull String id) {
    Optional<GameDocument> optionalGame = gameRepository.findById(id);
    if (optionalGame.isPresent()) {
      return mapper.map(optionalGame.get(), GameDto.class);
    }
    throw new EntityNotFoundException();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GameDto createGame(@RequestBody GameDto gameDto) {
    GameDocument game = mapper.map(gameDto, GameDocument.class);
    game = gameRepository.save(game);
    return mapper.map(game, GameDto.class);
  }
}
