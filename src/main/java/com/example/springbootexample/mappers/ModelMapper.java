package com.example.springbootexample.mappers;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class ModelMapper {

  private static final Converter<String, Duration> TO_DURATION = new AbstractConverter<>() {
    @Override
    protected Duration convert(String source) {
      return source == null ? null : Duration.parse(source);
    }
  };

  private static final Converter<Duration, String> FROM_DURATION = new AbstractConverter<>() {
    @Override
    protected String convert(Duration source) {
      return source == null ? null : source.toString();
    }
  };

  private final org.modelmapper.ModelMapper mapper;

  public ModelMapper() {
    mapper = new org.modelmapper.ModelMapper();
    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    mapper.addConverter(TO_DURATION);
    mapper.addConverter(FROM_DURATION);
  }

  public <T> T map(Object obj, Class<T> destinationClass) {
    return mapper.map(obj, destinationClass);
  }

  public <T, S> List<T> mapList(Iterable<S> objList, Class<T> destinationClass) {
    List<T> newList = new ArrayList<>();
    objList.forEach(obj -> newList.add(mapper.map(obj, destinationClass)));
    return newList;
  }
}
