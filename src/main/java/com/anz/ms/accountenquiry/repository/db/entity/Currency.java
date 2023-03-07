package com.anz.ms.accountenquiry.repository.db.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "name", nullable = false)
    private String name;
}