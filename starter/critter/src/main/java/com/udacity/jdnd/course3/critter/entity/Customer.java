package com.udacity.jdnd.course3.critter.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nationalized
    @Column(length=500)
    private String name;

    @Column(length=20)
    private String phoneNumber;

    @Column(length=5000)
    private String notes;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, targetEntity = Pet.class)
    private List<Pet> pets = new ArrayList<>();
}