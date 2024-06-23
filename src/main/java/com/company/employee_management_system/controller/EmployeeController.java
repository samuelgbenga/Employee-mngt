package com.company.employee_management_system.controller;

import com.company.employee_management_system.config.AdminCredential;
import com.company.employee_management_system.entity.Employee;
import com.company.employee_management_system.model.Admin;
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
    public String returnToIndex(Model model) {
        model.addAttribute("adminDetails", AdminCredential.getAdminCredentials());
       return "index";
    }
    @GetMapping("/{id}")
    public String returnToIndexById(@PathVariable Long id, Model model) {
        return "redirect:/employees";
    }

    @GetMapping("/list")
    public String getEmployees(@RequestHeader(value = "X-Requested-With", required = false) String requestedWith, Model model) {
        if ("XMLHttpRequest".equals(requestedWith)) {
            List<Employee> employees = employeeService.getAllEmployees();
            model.addAttribute("employees", employees);
            model.addAttribute("newEmployee", new Employee());
            model.addAttribute("adminDetails", AdminCredential.getAdminCredentials());
            return "employee/list"; // Use Thymeleaf fragment identifier
        } else {
            // Handle direct access or redirect to an error page or homepage
            return "redirect:/";
        }
    }

    @GetMapping("get/{id}")
    public String getEmployee(@PathVariable Long id, Model model) {

        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) { return "error/404";}
        model.addAttribute("employee", employee);
        model.addAttribute("adminDetails", AdminCredential.getAdminCredentials());
        return "employee/details";

    }


    @PostMapping
    public String createEmployee(@ModelAttribute Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/employees";

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
        return "redirect:/employees";

    }

    @GetMapping("delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }



  // not used at all

//    @GetMapping("edit/{id}")
//    public String updateEmployeeForm(@PathVariable Long id, Model model) {
//        Employee employee = employeeService.getEmployeeById(id);
//        model.addAttribute("employee", employee);
//        return employee != null ? "employee/form":"error/404";
//    }
//
//    @GetMapping("/new")
//    public String newEmployeeForm(Model model) {
//        Employee employee = new Employee();
//        model.addAttribute("employee", employee);
//        return "employee/form";
//    }

}
