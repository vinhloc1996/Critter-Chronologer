package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.exception.EmployeeNotFoundException;
import com.udacity.jdnd.course3.critter.exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmployeeRepository employeeRepository;



    public List<Pet> getPets(){
        return petRepository.findAll();
    }

    public Pet getPetById(Long petId){
        return petRepository.getPetById(petId);
    }


    public Pet savePet(Pet pet, Long cusId){
        Customer cus = customerRepository.getCustomerById(cusId);
        if (cus == null)
            throw new EmployeeNotFoundException();

        pet.setCustomer(cus);
        Pet newPet = petRepository.save(pet);

        cus.getPets().add(newPet);

        return newPet;
    }

    public void deletePetById(Long petId){

        Pet pet = petRepository.getPetById(petId);
        if (pet == null)
            throw new PetNotFoundException();

        petRepository.delete(pet);
    }

    public List<Pet> getPetByIds(List<Long> petIds) {
        return petRepository.findAllById(petIds);
    }

    public List<Pet> getPetsByCustomerId(Long cusId){
        List<Pet> petsByCustomer = petRepository.getPetsByCustomerId(cusId);

        return petsByCustomer;
    }
}
