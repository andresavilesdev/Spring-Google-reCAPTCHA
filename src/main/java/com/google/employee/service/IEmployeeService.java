package com.google.employee.service;

import com.google.employee.entity.EmployeeEntity;

import java.util.List;

public interface IEmployeeService {

    List<EmployeeEntity> findAll();

    EmployeeEntity findById(Long id);

    void save(EmployeeEntity employee);

    void delete(EmployeeEntity employee);

}
