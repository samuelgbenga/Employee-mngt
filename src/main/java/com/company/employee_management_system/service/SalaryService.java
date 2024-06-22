package com.company.employee_management_system.service;

import com.company.employee_management_system.entity.Salary;

import java.util.List;

public interface SalaryService {
    List<Salary> getAllSalaries();
    Salary getSalaryById(Long id);
    void saveSalary(Salary salary);
    void deleteSalary(Long id);
    // add get salary by user id
    List<Salary> getSalariesByEmployeeId(Long employeeId);

}
