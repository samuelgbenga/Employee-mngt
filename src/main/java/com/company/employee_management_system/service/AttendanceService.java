package com.company.employee_management_system.service;

import com.company.employee_management_system.entity.Attendance;

import java.util.List;

public interface AttendanceService {
    List<Attendance> getAllAttendance();
    Attendance getAttendanceById(Long id);
    void saveAttendance(Attendance attendance);
    void deleteAttendance(Long id);
    // late to include
    // get Attendance by user id
    List<Attendance> getAttendanceByEmployeeId(Long employeeId);

}
