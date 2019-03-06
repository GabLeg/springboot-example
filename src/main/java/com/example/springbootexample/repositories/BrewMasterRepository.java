package com.example.springbootexample.repositories;

import com.example.springbootexample.domain.brewMaster.BrewMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrewMasterRepository extends JpaRepository<BrewMaster, Long> {
}
