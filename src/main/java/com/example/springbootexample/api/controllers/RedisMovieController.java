package com.example.springbootexample.api.controllers;

import com.example.springbootexample.api.controllers.dto.MovieDto;
import com.example.springbootexample.infra.redis.MovieDocument;
import com.example.springbootexample.infra.redis.MovieRepository;
import com.example.springbootexample.mappers.ModelMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class RedisMovieController {

  private final MovieRepository movieRepository;
  private final ModelMapper mapper;

  @Autowired
  public RedisMovieController(MovieRepository movieRepository, ModelMapper modelMapper) {
    this.movieRepository = movieRepository;
    this.mapper = modelMapper;
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public MovieDto getMovieById(@PathVariable("id") @NonNull String id) {
    Optional<MovieDocument> optionalMovie = movieRepository.findById(id);
    if (optionalMovie.isPresent()) {
      return mapper.map(optionalMovie.get(), MovieDto.class);
    }
    throw new EntityNotFoundException();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MovieDto createMovie(@RequestBody MovieDto movieDto) {
    MovieDocument movie = mapper.map(movieDto, MovieDocument.class);
    movie = movieRepository.save(movie);
    return mapper.map(movie, MovieDto.class);
  }
}
