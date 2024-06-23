package com.company.employee_management_system.controller;


import com.company.employee_management_system.config.AdminCredential;
import com.company.employee_management_system.entity.Employee;
import com.company.employee_management_system.entity.Salary;
import com.company.employee_management_system.service.EmployeeService;
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
    private final EmployeeService employeeService;

    @Autowired
    public SalaryController(SalaryService salaryService, EmployeeService employeeService) {
        this.salaryService = salaryService;
        this.employeeService = employeeService;

    }

// map to index begin
    @GetMapping
    public String returnToIndex(Model model) {
        model.addAttribute("adminDetails", AdminCredential.getAdminCredentials());
        return "index";
    }
    @GetMapping("/{id}")
    public String returnToIndexById(@PathVariable Long id,Model model) {
        return "redirect:/salaries";
    }

    // map to index end

    //few list of salaries
    @GetMapping("/list")
    public String getAllSalaries(@RequestHeader(value = "X-Requested-With", required = false) String requestedWith, Model model) {
        if ("XMLHttpRequest".equals(requestedWith)) {
        List<Salary> salaries = salaryService.getAllSalaries();
        model.addAttribute("salaries", salaries);
        model.addAttribute("salaryNew", new Salary());
        model.addAttribute("employeeList", employeeService.getAllEmployees());
        model.addAttribute("adminDetails", AdminCredential.getAdminCredentials());
        return "salaries/list";
    }else {
            return "redirect:/";
        }
    }


    // do the real posting to the database

    @PostMapping
    public String addSalary(@ModelAttribute Salary salary) {

        if(salary.getEmployee().getId() == null){
            return "error/404";
        }

        salaryService.saveSalary(salary);
        return "redirect:/salaries";
    }


    // update the salary form the particular employee

    @PostMapping("/{id}")
    public String editSalary(@PathVariable Long id, @ModelAttribute Salary salaryDetails) {
        Salary salary = salaryService.getSalaryById(id);
        if(salary == null) return "error/404";
        salary.setSalary(salaryDetails.getSalary());
        salary.setDate(salaryDetails.getDate());
        salaryService.saveSalary(salary);

        return "redirect:/salaries";
    }

    @GetMapping("/delete/{id}")
    public String deleteSalary(@PathVariable Long id) {
        salaryService.deleteSalary(id);
        return "redirect:/salaries";
    }


    // methods not used at all
//    // get a salary input form
//    @GetMapping("/new")
//    public String addSalaryForm(Model model) {
//        model.addAttribute("salary", new Salary());
//        return "salaries/form";
//    }
//
//    // edit salary
//    @GetMapping("edit/{id}")
//    public String editSalaryForm(@PathVariable Long id, Model model) {
//
//        Salary salary = salaryService.getSalaryById(id);
//        model.addAttribute("salary", salary);
//        return salary!= null? "salaries/form": "error/404";
//
//    }
//    // custom employee jpa to get by employee id
//
//    @GetMapping("empSalaryList/{id}")
//    public String getSalaryByEmployeeId(@PathVariable Long id, Model model) {
//        List<Salary> salaries = salaryService.getSalariesByEmployeeId(id);
//        model.addAttribute("employee_salary_list", salaries);
//
//        return salaries != null ? "salaries/empSalaryList" : "error/404";
//    }
//
//    // get salary by id
//    @GetMapping("get/{id}")
//    public String getSalaryById(@PathVariable Long id, Model model) {
//        Salary salary = salaryService.getSalaryById(id);
//        model.addAttribute("salary", salary);
//        return salary != null ? "salary/detail" : "error/404";
//    }

}
