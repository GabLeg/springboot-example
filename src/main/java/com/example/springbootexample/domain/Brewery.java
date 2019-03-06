package com.example.springbootexample.domain;

import com.example.springbootexample.domain.brewMaster.BrewMaster;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "BREWERY")
public class Brewery {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE")
    private String phone;

    @ManyToMany
    @JoinTable(name = "BREWERY_BREW_MASTER",
            joinColumns = @JoinColumn(name = "BREWERY_ID"),
            inverseJoinColumns = @JoinColumn(name = "BREW_MASTER_ID"))
    private List<BrewMaster> employees;

    @OneToMany(mappedBy = "brewery")
    private List<Beer> beerList;
}
