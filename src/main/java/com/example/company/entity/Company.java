package com.example.company.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "company")
public class Company extends AbstractEntity {

    private Long bill;

}
