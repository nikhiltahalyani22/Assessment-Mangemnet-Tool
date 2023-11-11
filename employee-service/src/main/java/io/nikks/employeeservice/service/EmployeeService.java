package io.nikks.employeeservice.service;

import io.nikks.employeeservice.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<Employee> findAllEmployee();

    void scheduleAssessment(String assessmentType, String assessmentName, String assessmentDate);

    Map<String,List<String>> findAllAssessments(Long username);
}
