package com.udacity.jdnd.course3.critter.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Table(name="employee")
@Setter @Getter @NoArgsConstructor @EqualsAndHashCode
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Nationalized
    String name;

    @ElementCollection
    @CollectionTable(name="daysAvailable")
    @Enumerated(EnumType.STRING)
    @Column(name = "daysAvailable", nullable = false)
    private Set<DayOfWeek> daysAvailable;

    @ElementCollection
    @CollectionTable(name="skills")
    @Enumerated(EnumType.STRING)
    @Column(name = "skills", nullable = false)
    private Set<EmployeeSkill> skills;

}
