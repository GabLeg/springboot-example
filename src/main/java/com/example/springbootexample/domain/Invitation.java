package com.example.springbootexample.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Invitation {

    private String location;
    private String date;
    private String recipient;
}
