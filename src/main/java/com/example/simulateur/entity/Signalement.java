package com.example.simulateur.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Signalement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
}
