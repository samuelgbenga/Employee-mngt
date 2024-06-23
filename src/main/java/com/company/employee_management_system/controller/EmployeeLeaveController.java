package com.company.employee_management_system.controller;


import com.company.employee_management_system.config.AdminCredential;
import com.company.employee_management_system.entity.EmployeeLeave;
import com.company.employee_management_system.service.EmployeeLeaveService;
import com.company.employee_management_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/leaves")
public class EmployeeLeaveController {

    private final EmployeeLeaveService employeeLeaveService;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeLeaveController(EmployeeLeaveService employeeLeaveService, EmployeeService employeeService) {
        this.employeeLeaveService = employeeLeaveService;
        this.employeeService = employeeService;

    }

    // map to index begin
    @GetMapping
    public String returnToIndex(Model model) {
        model.addAttribute("adminDetails", AdminCredential.getAdminCredentials());
        return "index";
    }
    @GetMapping("/{id}")
    public String returnToIndexById(@PathVariable Long id) {
        return "redirect:/leaves";
    }
    // map to index end

    @GetMapping("/list")
    public String getAllEmployeeLeaves(@RequestHeader(value = "X-Requested-With", required = false) String requestedWith, Model model) {

        if ("XMLHttpRequest".equals(requestedWith)) {
        List<EmployeeLeave> employeeLeaves = employeeLeaveService.getAllEmployeeLeave();
        model.addAttribute("employeeLeaves", employeeLeaves);
        model.addAttribute("newEmployeeLeaves", new EmployeeLeave());
        model.addAttribute("employeeList", employeeService.getAllEmployees());
        model.addAttribute("adminDetails", AdminCredential.getAdminCredentials());

        return "leaves/list";
        }else {
            return "redirect:/";
        }

    }


    @PostMapping
    public String newEmployeeLeave(@ModelAttribute EmployeeLeave employeeLeave) {

        if(employeeLeave.getEmployee().getId() == null){
            return "error/404";
        }

        employeeLeaveService.saveEmployeeLeave(employeeLeave);
        return "redirect:/leaves";
    }


    @PostMapping("/{id}")
    public String editEmployeeLeave(@PathVariable Long id, @ModelAttribute EmployeeLeave employeeLeaveDetails) {
        EmployeeLeave employeeLeave = employeeLeaveService.getEmployeeLeaveById(id);

        if(employeeLeave == null) { return "error/404";}

        employeeLeave.setStartDate(employeeLeaveDetails.getStartDate());
        employeeLeave.setEndDate(employeeLeaveDetails.getEndDate());
        employeeLeave.setReason(employeeLeaveDetails.getReason());

        employeeLeaveService.saveEmployeeLeave(employeeLeave);
        return "redirect:/leaves";

    }

    @GetMapping("/delete/{id}")
    public String deleteEmployeeLeave(@PathVariable Long id) {
        employeeLeaveService.deleteEmployeeLeave(id);
        return "redirect:/leaves";
    }


// not used methods

    // get a salary form to input new salary info

//    @GetMapping("/new")
//    public String newEmployeeLeaveForm(Model model) {
//        EmployeeLeave employeeLeave = new EmployeeLeave();
//        model.addAttribute("employeeLeave", employeeLeave);
//        return "leaves/form";
//    }
//
//    // update the salary info form
//    @GetMapping("edit/{id}")
//    public String editEmployeeLeaveForm(@PathVariable Long id, Model model) {
//
//        EmployeeLeave employeeLeave = employeeLeaveService.getEmployeeLeaveById(id);
//        model.addAttribute("employeeLeave", employeeLeave);
//        return employeeLeave != null ?"leaves/form": "error/404";
//    }
//
//    // custom jpa extension to get by user id
//    @GetMapping("/empLeaveList/{id}")
//    public String getEmployeeLeaveByEmployeeId(@PathVariable Long id, Model model) {
//        List<EmployeeLeave> employeeLeaves = employeeLeaveService.getEmployeeLeaveByEmployeeId(id);
//        model.addAttribute("employee_leave_list", employeeLeaves);
//        return employeeLeaves != null ? "leaves/employeeLeaveList": "error/404";
//    }
//
//
//    // get the salaries by id
//    @GetMapping("get/{id}")
//    public String getEmployeeLeaveById(@PathVariable Long id, Model model) {
//        EmployeeLeave employeeLeave = employeeLeaveService.getEmployeeLeaveById(id);
//        model.addAttribute("employeeLeaves", employeeLeave);
//
//        return employeeLeave != null ? "leaves/details": "error/404";
//
//    }
}
