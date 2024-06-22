package com.company.employee_management_system.controller;

import java.util.List;

import com.company.employee_management_system.entity.Attendance;
import com.company.employee_management_system.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
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
    public String getAttendances(Model model) {
        List<Attendance> attendanceList = attendanceService.getAllAttendance();
        model.addAttribute("attendanceList", attendanceList);
        model.addAttribute("newAttendance", new Attendance());
        return "attendance/list";
    }

    // get attendance by id
    @GetMapping("get/{id}")
    public String getAttendanceById(@PathVariable Long id, Model model) {
        Attendance attendance = attendanceService.getAttendanceById(id);
        model.addAttribute("attendance", attendance);
        return attendance!=null ? "attendance/detail": "error/404";
    }

    //get a new Attendance form
    @GetMapping("/new")
    public String newAttendance(Model model) {
        model.addAttribute("attendance", new Attendance());
        return "attendance/new";


    }

    @PostMapping
    public String saveAttendance(@ModelAttribute Attendance attendance) {
        // check the user by name if the user exist
        // check or confirm that the user information tallies
        // with the one on the database

        if(attendance.getEmployee().getId()==null) {

            return "error/404";
        }

        attendanceService.saveAttendance(attendance);
        return "index";
    }

    // update attendance form
    @GetMapping("/edit/{id}")
    public String updateAttendanceForm(@PathVariable Long id, Model model){
        Attendance attendance = attendanceService.getAttendanceById(id);
        model.addAttribute("attendance", attendance);
        return attendance != null? "attendance/new": "error/404";
    }

    // save new attendance info
    @PostMapping("/{id}")
    public String updateAttendance(@PathVariable Long id, @ModelAttribute Attendance attendanceDetails){
        Attendance attendance = attendanceService.getAttendanceById(id);
        if(attendance == null){
            return "error/404";
        }
        attendance.setDate(attendanceDetails.getDate());
        attendance.setPresent(attendanceDetails.isPresent());
        attendanceService.saveAttendance(attendance);
        return "index";
    }

    // delete attendance after fulfilment
    @GetMapping("/delete/{id}")
    public String deleteAttendance(@PathVariable Long id){

        attendanceService.deleteAttendance(id);

        return "index";
    }

    // costume jpa method to get by user id

    @GetMapping("empAttendanceList/{id}")
    public String getAttendanceByEmployeeId(@PathVariable Long id, Model model){
       List<Attendance> attendance = attendanceService.getAttendanceByEmployeeId(id);

       model.addAttribute("employee_attendance_list", attendance);

       return attendance!=null ? "attendance/empAttendanceList": "error/404";


    }

    // to later display in order of date
}
