package com.example.springbootexample.infra.repository;

import com.example.springbootexample.domain.object.Goody;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "goodies")
public interface GoodyRepository extends CrudRepository<Goody, Long> {
}
