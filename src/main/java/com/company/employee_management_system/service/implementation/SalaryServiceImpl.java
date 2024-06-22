package com.company.employee_management_system.service.implementation;

import com.company.employee_management_system.entity.Salary;
import com.company.employee_management_system.repository.SalaryRepository;
import com.company.employee_management_system.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {
    SalaryRepository salaryRepository;

    @Autowired
    public SalaryServiceImpl(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    @Override
    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }

    @Override
    public Salary getSalaryById(Long id) {
        return salaryRepository.findById(id).orElse(null);
    }

    @Override
    public void saveSalary(Salary salary) {
         salaryRepository.save(salary);
    }


    @Override
    public void deleteSalary(Long id) {
            salaryRepository.deleteById(id);
    }

    @Override
    public List<Salary> getSalariesByEmployeeId(Long employeeId) {
        return salaryRepository.findByEmployeeId(employeeId);
    }
}
