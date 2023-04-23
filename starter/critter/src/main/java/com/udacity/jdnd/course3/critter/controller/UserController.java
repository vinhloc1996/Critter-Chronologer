package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.exception.EmployeeNotFoundException;
import com.udacity.jdnd.course3.critter.exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = customerService.saveCustomer(mapDtoToCustomer(customerDTO));
        return mapCustomerToDto(customer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerService.getCustomers();
        List<CustomerDTO> listCustomerDto = new ArrayList<>();

        for (Customer cus : customers){
            listCustomerDto.add(mapCustomerToDto(cus));
        }

        return listCustomerDto;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Pet p = petService.getPetById(petId);
        if (p == null)
            throw new PetNotFoundException();

        return mapCustomerToDto(p.getCustomer());
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.saveEmployee(mapDtoToEmployee(employeeDTO));
        return mapEmployeeToDto(employee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee e = employeeService.getEmployee(employeeId);
        if (e == null)
            throw new EmployeeNotFoundException();

        return mapEmployeeToDto(e);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Employee e = employeeService.getEmployee(employeeId);
        if (e == null)
            throw new EmployeeNotFoundException();

        e.setDaysAvailable(daysAvailable);
        employeeService.saveEmployee(e);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees = employeeService.getAvailableEmployees(employeeDTO.getDate(), employeeDTO.getSkills());

        List<EmployeeDTO> listEmployeeDto = new ArrayList<>();
        if (employees != null && employees.size() > 0){
            for (Employee employee : employees){
                listEmployeeDto.add(mapEmployeeToDto(employee));
            }
        }

        return listEmployeeDto;
    }

    private Customer mapDtoToCustomer(CustomerDTO customerDTO){
        Customer c = new Customer();
        BeanUtils.copyProperties(customerDTO, c, "id");

        return c;
    }

    private Employee mapDtoToEmployee(EmployeeDTO employeeDTO){
        Employee e = new Employee();
        BeanUtils.copyProperties(employeeDTO, e, "id");

        return e;
    }

    private EmployeeDTO mapEmployeeToDto(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(employee, dto);
        return dto;
    }

    private CustomerDTO mapCustomerToDto(Customer c){
        CustomerDTO dto = new CustomerDTO();
        BeanUtils.copyProperties(c, dto);
        c.getPets().forEach( pet -> {
            dto.getPetIds().add(pet.getId());
        });
        return dto;
    }
}
