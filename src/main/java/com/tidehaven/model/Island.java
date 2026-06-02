package com.tidehaven.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "islands")
@Data
public class Island {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Lob
    private String worldJson;
}