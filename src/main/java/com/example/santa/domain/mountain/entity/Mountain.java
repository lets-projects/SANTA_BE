package com.example.santa.domain.mountain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="mountains")
public class Mountain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private double height;
    private String description;
    private String point;
    private String transportation;
    private double longitude;
    private double latitude;
}
