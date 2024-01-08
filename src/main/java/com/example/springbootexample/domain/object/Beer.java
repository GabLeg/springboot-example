package com.example.springbootexample.domain.object;

import com.example.springbootexample.domain.object.brewMaster.BrewMaster;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "BEER")
public class Beer {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "COLOR")
  private String color;

  @Column(name = "IBU")
  private Integer ibu;

  @Column(name = "ALCOHOL")
  private Float alcohol;

  @Column(name = "PRICE")
  private BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "FK_BREWERY", nullable = false, updatable = false)
  private Brewery brewery;

  @ManyToOne
  @JoinColumn(name = "FK_BREW_MASTER", nullable = false, updatable = false)
  private BrewMaster creator;
}
