package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query("Select p from Pet p where p.customer.id = :cusId")
    List<Pet> getPetsByCustomerId(@Param("cusId") Long cusId);

    @Query("Select p from Pet p where p.id = :petId")
    Pet getPetById(@Param("petId") Long petId);
}
