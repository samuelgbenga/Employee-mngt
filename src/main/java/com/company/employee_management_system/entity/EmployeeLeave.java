package com.company.employee_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;




@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
}
