package com.google.service;

import com.google.entities.EmployeeEntity;

import java.util.List;

public interface IEmployeeService {

    List<EmployeeEntity> findAll();

    EmployeeEntity findById(Long id);

    void save(EmployeeEntity employee);

    void delete(EmployeeEntity employee);

}
