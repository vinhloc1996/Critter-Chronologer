package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PetService petService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public List<Schedule> getSchedules(){
        return scheduleRepository.getSchedules();
    }

    public Schedule getScheduleById(Long scheduleId){
        return scheduleRepository.getSchedulyById(scheduleId);
    }

    public List<Schedule> getSchedulesByPetId(Long petId){
        return scheduleRepository.getScheduleByPetId(petId);
    }

    public List<Schedule> getSchedulesByEmployeeId(Long empId){
        return scheduleRepository.getScheduleByEmployeeId(empId);
    }

    public List<Schedule> getSchedulesByCustomerId(Long cusId){
        return scheduleRepository.getScheduleByCustomerId(cusId);
    }

    public Schedule saveSchedule(Schedule schedule){
        Schedule newSchedule = scheduleRepository.save(schedule);

        return newSchedule;
    }
}
