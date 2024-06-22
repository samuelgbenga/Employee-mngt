package com.company.employee_management_system.service.implementation;

import com.company.employee_management_system.entity.EmployeeLeave;
import com.company.employee_management_system.repository.EmployeeLeaveRepository;
import com.company.employee_management_system.service.EmployeeLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeLeaveServiceImpl implements EmployeeLeaveService {

    EmployeeLeaveRepository employeeLeaveRepository;

    @Autowired
    public EmployeeLeaveServiceImpl(EmployeeLeaveRepository employeeLeaveRepository) {
        this.employeeLeaveRepository = employeeLeaveRepository;
    }


    @Override
    public List<EmployeeLeave> getAllEmployeeLeave() {
        return employeeLeaveRepository.findAll();
    }

    @Override
    public EmployeeLeave getEmployeeLeaveById(Long id) {
        return employeeLeaveRepository.findById(id).orElse(null);
    }

    @Override
    public void saveEmployeeLeave(EmployeeLeave employeeLeave) {
        employeeLeaveRepository.save(employeeLeave);
    }


    @Override
    public void deleteEmployeeLeave(Long id) {
            employeeLeaveRepository.deleteById(id);
    }

    @Override
    public List<EmployeeLeave> getEmployeeLeaveByEmployeeId(Long id) {
        return employeeLeaveRepository.findByEmployeeId(id);
    }
}
