package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.EmployeeSkill;
import com.udacity.jdnd.course3.critter.exception.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.exception.EmployeeNotFoundException;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long empId) {
        return employeeRepository.getEmployeeById(empId);
    }

    public Employee saveEmployee(Employee employee) {

        Employee emp = employeeRepository.save(employee);

        return emp;
    }

    public void deleteEmployeeById(Long empId){

        Employee employee = employeeRepository.getEmployeeById(empId);

        if (employee == null)
            throw new EmployeeNotFoundException();

        employeeRepository.delete(employee);
    }


    public List<Employee> getAvailableEmployees(LocalDate date, Set<EmployeeSkill> skills) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        List<Employee> availableEmployees = employeeRepository.getEmployeesByDaysAvailable(dayOfWeek);

        List<Employee> filterSkills = availableEmployees.stream()
                .filter(e -> e.getSkills().containsAll(skills))
                .collect(toList());


        return filterSkills;
    }
}
