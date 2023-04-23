package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query(
    "SELECT e from Employee e" +
    " WHERE :dow MEMBER OF e.daysAvailable"
    )
    List<Employee> getEmployeesByDaysAvailable(@Param("dow") DayOfWeek dow);

    @Query("Select e from Employee e where e.id = :empId")
    Employee getEmployeeById(@Param("empId") Long empId);
}