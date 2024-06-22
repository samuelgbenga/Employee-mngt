package com.company.employee_management_system.controller;


import com.company.employee_management_system.entity.EmployeeLeave;
import com.company.employee_management_system.service.EmployeeLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/leaves")
public class EmployeeLeaveController {

    private final EmployeeLeaveService employeeLeaveService;

    @Autowired
    public EmployeeLeaveController(EmployeeLeaveService employeeLeaveService) {
        this.employeeLeaveService = employeeLeaveService;
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

    @GetMapping("/list")
    public String getAllEmployeeLeaves(Model model) {
        List<EmployeeLeave> employeeLeaves = employeeLeaveService.getAllEmployeeLeave();
        model.addAttribute("employeeLeaves", employeeLeaves);
        model.addAttribute("newEmployeeLeaves", new EmployeeLeave());
        return "leaves/list";

    }

    // get the salaries by id
    @GetMapping("get/{id}")
    public String getEmployeeLeaveById(@PathVariable Long id, Model model) {
        EmployeeLeave employeeLeave = employeeLeaveService.getEmployeeLeaveById(id);
        model.addAttribute("employeeLeaves", employeeLeave);

        return employeeLeave != null ? "leaves/details": "error/404";

    }

    // get a salary form to input new salary info

    @GetMapping("/new")
    public String newEmployeeLeaveForm(Model model) {
        EmployeeLeave employeeLeave = new EmployeeLeave();
        model.addAttribute("employeeLeave", employeeLeave);
        return "leaves/form";
    }

    @PostMapping
    public String newEmployeeLeave(@ModelAttribute EmployeeLeave employeeLeave) {

        if(employeeLeave.getEmployee().getId() == null){
            return "error/404";
        }

        employeeLeaveService.saveEmployeeLeave(employeeLeave);
        return "index";
    }

    // update the salary info form
    @GetMapping("edit/{id}")
    public String editEmployeeLeaveForm(@PathVariable Long id, Model model) {

        EmployeeLeave employeeLeave = employeeLeaveService.getEmployeeLeaveById(id);
        model.addAttribute("employeeLeave", employeeLeave);
        return employeeLeave != null ?"leaves/form": "error/404";
    }

    @PostMapping("/{id}")
    public String editEmployeeLeave(@PathVariable Long id, @ModelAttribute EmployeeLeave employeeLeaveDetails) {
        EmployeeLeave employeeLeave = employeeLeaveService.getEmployeeLeaveById(id);

        if(employeeLeave == null) { return "error/404";}

        employeeLeave.setStartDate(employeeLeaveDetails.getStartDate());
        employeeLeave.setEndDate(employeeLeaveDetails.getEndDate());
        employeeLeave.setReason(employeeLeaveDetails.getReason());

        employeeLeaveService.saveEmployeeLeave(employeeLeave);
        return "index";

    }

    @GetMapping("/delete/{id}")
    public String deleteEmployeeLeave(@PathVariable Long id) {
        employeeLeaveService.deleteEmployeeLeave(id);
        return "index";
    }

    // custom jpa extension to get by user id
    @GetMapping("/empLeaveList/{id}")
    public String getEmployeeLeaveByEmployeeId(@PathVariable Long id, Model model) {
        List<EmployeeLeave> employeeLeaves = employeeLeaveService.getEmployeeLeaveByEmployeeId(id);
        model.addAttribute("employee_leave_list", employeeLeaves);
        return employeeLeaves != null ? "leaves/employeeLeaveList": "error/404";
    }
}
