package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.exception.EmployeeNotFoundException;
import com.udacity.jdnd.course3.critter.exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet p = petService.savePet(mapDtoToPet(petDTO), petDTO.getOwnerId());
        PetDTO newPetDto = mapPetToDto(p);
        newPetDto.setOwnerId(p.getCustomer().getId());

        return newPetDto;

    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet p = petService.getPetById(petId);
        if (p == null)
            throw new PetNotFoundException();

        PetDTO petDto = mapPetToDto(p);
        petDto.setOwnerId(p.getCustomer().getId());
        return petDto;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.getPets();
        List<PetDTO> listPetDto = new ArrayList<>();

        for (Pet pet : pets){
            listPetDto.add(mapPetToDto(pet));
        }
        return listPetDto;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.getPetsByCustomerId(ownerId);
        List<PetDTO> listPetDto = new ArrayList<>();

        for (Pet pet : pets){
            listPetDto.add(mapPetToDto(pet));
        }
        return listPetDto;
    }

    private Pet mapDtoToPet(PetDTO petDTO){
        Pet p = new Pet();
        BeanUtils.copyProperties(petDTO, p, "id");

        return p;
    }

    private PetDTO mapPetToDto(Pet pet) {
        PetDTO dto = new PetDTO();
        BeanUtils.copyProperties(pet, dto);
        dto.setOwnerId(pet.getCustomer().getId());
        return dto;
    }
}
