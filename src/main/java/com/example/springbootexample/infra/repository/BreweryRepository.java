package com.example.springbootexample.infra.repository;

import com.example.springbootexample.domain.object.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreweryRepository extends JpaRepository<Brewery, Long> {
}
