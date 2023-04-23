package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.exception.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long cusId) {
        return customerRepository.getCustomerById(cusId);
    }

    public Customer saveCustomer(Customer customer) {

        Customer cus = customerRepository.save(customer);

        return cus;
    }

    public void deleteCustomerById(Long cusId){

        Customer customer = customerRepository.getCustomerById(cusId);

        if (customer == null)
            throw new CustomerNotFoundException();

        customerRepository.delete(customer);
    }

    public Customer getCustomerByPetId(Long petId){
        Customer customer = customerRepository.getCustomerByPetId(petId);

        return customer;
    }
}