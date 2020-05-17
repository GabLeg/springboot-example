package com.example.springbootexample.mappers;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelMapper {

    private final org.modelmapper.ModelMapper mapper;

    public ModelMapper() {
        mapper = new org.modelmapper.ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public <T> T map(Object obj, Class<T> destinationClass) {
        return mapper.map(obj, destinationClass);
    }

    public <T,S> List<T> mapList(Iterable<S> objList, Class<T> destinationClass) {
        List<T> newList = new ArrayList<>();
        objList.forEach(obj -> newList.add(mapper.map(obj, destinationClass)));
        return newList;
    }
}
