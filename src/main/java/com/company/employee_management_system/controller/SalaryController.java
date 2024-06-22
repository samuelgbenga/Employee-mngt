package com.company.employee_management_system.controller;


import com.company.employee_management_system.entity.Salary;
import com.company.employee_management_system.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/salaries")
public class SalaryController {

    private final SalaryService salaryService;

    @Autowired
    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

// map to index begin
    @GetMapping
    public String returnToIndex() {
        return "index";
    }
    @GetMapping("/{id}")
    public String returnToIndexById(@PathVariable Long id) {
        return "index";
    }

    // map to index end

    //few list of salaries
    @GetMapping("/list")
    public String getAllSalaries(Model model) {
        List<Salary> salaries = salaryService.getAllSalaries();
        model.addAttribute("salaries", salaries);
        model.addAttribute("salaryNew", new Salary());
        return "salaries/list";
    }

    // get salary by id
    @GetMapping("get/{id}")
    public String getSalaryById(@PathVariable Long id, Model model) {
        Salary salary = salaryService.getSalaryById(id);
        model.addAttribute("salary", salary);
        return salary != null ? "salary/detail" : "error/404";
    }

    // get a salary input form
    @GetMapping("/new")
    public String addSalaryForm(Model model) {
        model.addAttribute("salary", new Salary());
        return "salaries/form";
    }

    // do the real posting to the database

    @PostMapping
    public String addSalary(@ModelAttribute Salary salary) {

        if(salary.getEmployee().getId() == null){
            return "error/404";
        }

        salaryService.saveSalary(salary);
        return "index";
    }

    // edit salary
    @GetMapping("edit/{id}")
    public String editSalaryForm(@PathVariable Long id, Model model) {

        Salary salary = salaryService.getSalaryById(id);
        model.addAttribute("salary", salary);
        return salary!= null? "salaries/form": "error/404";

    }

    // update the salary form the particular employee

    @PostMapping("/{id}")
    public String editSalary(@PathVariable Long id, @ModelAttribute Salary salaryDetails) {
        Salary salary = salaryService.getSalaryById(id);
        if(salary == null) return "error/404";
        salary.setSalary(salaryDetails.getSalary());
        salary.setDate(salaryDetails.getDate());
        salaryService.saveSalary(salary);

        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteSalary(@PathVariable Long id) {
        salaryService.deleteSalary(id);
        return "index";
    }

    // custom employee jpa to get by employee id

    @GetMapping("empSalaryList/{id}")
    public String getSalaryByEmployeeId(@PathVariable Long id, Model model) {
        List<Salary> salaries = salaryService.getSalariesByEmployeeId(id);
        model.addAttribute("employee_salary_list", salaries);

        return salaries != null ? "salaries/empSalaryList" : "error/404";
    }

}
