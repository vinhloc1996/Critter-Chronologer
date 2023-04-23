package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("Select s from Schedule s")
    List<Schedule> getSchedules();

    @Query("Select s from Schedule s where s.id = :id")
    Schedule getSchedulyById(@Param("id") Long id);

    @Query(
    "SELECT s from Schedule s" +
    " INNER JOIN s.pets p" +
    " WHERE p.id = :petId"
    )
    List<Schedule> getScheduleByPetId(@Param("petId") Long petId);

    @Query(
    "SELECT s from Schedule s" +
    " INNER JOIN s.employees e" +
    " WHERE e.id = :empId"
    )
    List<Schedule> getScheduleByEmployeeId(@Param("empId") Long empId);

    @Query(
    "SELECT s from Schedule s" +
    " INNER JOIN s.pets p" +
    " INNER JOIN p.customer c" +
    " WHERE c.id = :cusId"
    )
    List<Schedule> getScheduleByCustomerId(@Param("cusId") Long cusId);
}
