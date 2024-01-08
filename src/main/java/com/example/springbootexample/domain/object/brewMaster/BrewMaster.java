package com.example.springbootexample.domain.object.brewMaster;

import com.example.springbootexample.domain.object.Beer;
import com.example.springbootexample.domain.object.Brewery;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "BREW_MASTER")
public class BrewMaster {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "EXPERIENCE")
  @Enumerated(EnumType.STRING)
  private Experience experience;

  @OneToMany(mappedBy = "creator")
  private List<Beer> creations;

  @ManyToMany(mappedBy = "employees")
  private List<Brewery> workplaces;
}
