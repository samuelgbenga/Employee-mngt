package com.company.employee_management_system.service.implementation;

import com.company.employee_management_system.entity.Employee;
import com.company.employee_management_system.repository.EmployeeRepository;
import com.company.employee_management_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
   // instantiate the respective repo
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // inject dependency into the constructor


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }



    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
