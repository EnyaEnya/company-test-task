package com.example.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "measure")
public class Measure extends AbstractEntity {

    @Id
    private Long id;

    private String name;

}
