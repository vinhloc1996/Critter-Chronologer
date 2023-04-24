package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    CustomerService customerService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule s = mapDtoToSchedule(scheduleDTO);
        s.setEmployees(employeeService.getEmployeeByIds(scheduleDTO.getEmployeeIds()));
        s.setPets(petService.getPetByIds(scheduleDTO.getPetIds()));

        Schedule schedule = scheduleService.saveSchedule(s);
        return mapScheduleToDto(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getSchedules();
        List<ScheduleDTO> listScheduleDto = new ArrayList<>();

        for (Schedule schedule : schedules){
            listScheduleDto.add(mapScheduleToDto(schedule));
        }
        return listScheduleDto;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getSchedulesByPetId(petId);
        List<ScheduleDTO> listScheduleDto = new ArrayList<>();

        for (Schedule schedule : schedules){
            listScheduleDto.add(mapScheduleToDto(schedule));
        }

        return listScheduleDto;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getSchedulesByEmployeeId(employeeId);
        List<ScheduleDTO> listScheduleDto = new ArrayList<>();

        for (Schedule schedule : schedules){
            listScheduleDto.add(mapScheduleToDto(schedule));
        }

        return listScheduleDto;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getSchedulesByCustomerId(customerId);
        List<ScheduleDTO> listScheduleDto = new ArrayList<>();

        for (Schedule schedule : schedules){
            listScheduleDto.add(mapScheduleToDto(schedule));
        }

        return listScheduleDto;
    }

    private Schedule mapDtoToSchedule(ScheduleDTO scheduleDTO){
        Schedule s = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, s, "id");

        return s;
    }

    private ScheduleDTO mapScheduleToDto(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, dto);
        List<Long> empIds = new ArrayList<>();
        empIds = schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList());
        List<Long> petIds = new ArrayList<>();
        petIds = schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList());
        dto.setEmployeeIds(empIds);
        dto.setPetIds(petIds);
        return dto;
    }
}
