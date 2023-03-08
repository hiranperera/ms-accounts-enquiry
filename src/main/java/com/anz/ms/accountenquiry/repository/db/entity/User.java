package com.anz.ms.accountenquiry.repository.db.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", nullable = false)
    private String userCode;

    @Column(name = "name", nullable = false)
    private String name;
}
