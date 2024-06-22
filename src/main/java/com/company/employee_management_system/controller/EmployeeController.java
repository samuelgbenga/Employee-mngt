package com.company.employee_management_system.controller;

import com.company.employee_management_system.entity.Employee;
import com.company.employee_management_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping
    public String returnToIndex() {
       return "index";
    }
    @GetMapping("/{id}")
    public String returnToIndexById(@PathVariable Long id) {
        return "index";
    }

    @GetMapping("/list")
    public String getEmployees(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees",  employees);
        model.addAttribute("newEmployee", new Employee());
        return "employee/list";
    }

    @GetMapping("get/{id}")
    public String getEmployee(@PathVariable Long id, Model model) {

        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) { return "error/404";}
        model.addAttribute("employee", employee);

        return "employee/details";

    }

    @GetMapping("/new")
    public String newEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee/form";
    }

    @PostMapping
    public String createEmployee(@ModelAttribute Employee employee){
        employeeService.saveEmployee(employee);
        return "index";

    }

    @GetMapping("edit/{id}")
    public String updateEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return employee != null ? "employee/form":"error/404";
    }

    @PostMapping("/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employeeDetails){

        Employee employee = employeeService.getEmployeeById(id);
        if(employee==null){
            return "error/404";
        }
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setDepartment(employeeDetails.getDepartment());
        employeeService.saveEmployee(employee);
        return "index";

    }

    @GetMapping("delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return "index";
    }

}
