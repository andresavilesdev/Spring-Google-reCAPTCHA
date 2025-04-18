package com.google.employee.service;

import com.google.employee.entity.EmployeeEntity;
import com.google.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeEntity> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeEntity findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void save(EmployeeEntity employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void delete(EmployeeEntity employee) {
        employeeRepository.delete(employee);
    }
}
