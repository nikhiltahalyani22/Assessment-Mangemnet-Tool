package io.nikks.employeeservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "empid")
    private Long empId;
    @Column(name = "atype")
    private String assessmentType;
    @Column(name = "aname")
    private String assessmentName;
    @Column(name = "adate")
    private String assessmentDate;

    public Employee(Long empId, String assessmentType, String assessmentName, String assessmentDate) {
        this.empId = empId;
        this.assessmentType = assessmentType;
        this.assessmentName = assessmentName;
        this.assessmentDate = assessmentDate;
    }
}

