package com.company.employee_management_system.service.implementation;

import com.company.employee_management_system.entity.Attendance;
import com.company.employee_management_system.repository.AttendanceRepository;
import com.company.employee_management_system.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
   private final AttendanceRepository attendanceRepository;

   @Autowired
   public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
       this.attendanceRepository = attendanceRepository;
   }


    @Override
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    @Override
    public Attendance getAttendanceById(Long id) {
        return attendanceRepository.findById(id).orElse(null);
    }

    @Override
    public void saveAttendance(Attendance attendance) {
         attendanceRepository.save(attendance);
    }


    @Override
    public void deleteAttendance(Long id) {
       attendanceRepository.deleteById(id);

    }

    @Override
    public List<Attendance> getAttendanceByEmployeeId(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }
}
