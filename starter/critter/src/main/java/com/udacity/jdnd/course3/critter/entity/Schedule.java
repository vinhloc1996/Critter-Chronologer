package com.udacity.jdnd.course3.critter.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(targetEntity = Employee.class)
    @JoinTable(name = "schedule_employee", inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;

    @ManyToMany(targetEntity = Pet.class)
    @JoinTable(name = "schedule_pet", inverseJoinColumns = @JoinColumn(name = "pet_id"))
    private List<Pet> pets;

    private LocalDate date;

    @ElementCollection(targetClass = EmployeeSkill.class)
    @CollectionTable(name="schedule_activities", joinColumns = @JoinColumn(name="id"))
    @Column(name="activities", nullable = false)
    private Set<EmployeeSkill> activities;



}