package com.company.employee_management_system.service;

import com.company.employee_management_system.entity.EmployeeLeave;

import java.util.List;

public interface EmployeeLeaveService {
    List<EmployeeLeave> getAllEmployeeLeave();
    EmployeeLeave getEmployeeLeaveById(Long id);
    void saveEmployeeLeave(EmployeeLeave employeeLeave);
    void deleteEmployeeLeave(Long id);
    // later to add
    // get Employee Leave by userId
    List<EmployeeLeave> getEmployeeLeaveByEmployeeId(Long id);
}
