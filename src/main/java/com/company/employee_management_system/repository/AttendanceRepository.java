package com.company.employee_management_system.repository;

import com.company.employee_management_system.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
        List<Attendance> findByEmployeeId(Long employeeId);

}
