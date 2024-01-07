package com.example.springbootexample.infra.repository;

import com.example.springbootexample.domain.object.brewMaster.BrewMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrewMasterRepository extends JpaRepository<BrewMaster, Long> {
}
