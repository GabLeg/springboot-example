package com.example.springbootexample.infra.redis;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<MovieDocument, String> {
}
