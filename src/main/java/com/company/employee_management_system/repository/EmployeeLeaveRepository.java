package com.company.employee_management_system.repository;

import com.company.employee_management_system.entity.EmployeeLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Long> {

    List<EmployeeLeave> findByEmployeeId(Long employeeId);

}
