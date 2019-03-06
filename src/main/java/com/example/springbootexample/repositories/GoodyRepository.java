package com.example.springbootexample.repositories;

import com.example.springbootexample.domain.Goody;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "goodies")
public interface GoodyRepository extends CrudRepository<Goody, Long> {
}
