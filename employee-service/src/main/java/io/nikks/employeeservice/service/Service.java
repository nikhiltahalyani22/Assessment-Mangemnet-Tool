package io.nikks.employeeservice.service;

import io.nikks.employeeservice.config.AdminServiceClient;
import io.nikks.employeeservice.entity.Employee;
import io.nikks.employeeservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class Service implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final AdminServiceClient adminServiceClient;
    private long empId=0;
    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public void scheduleAssessment(String assessmentType, String assessmentName, String assessmentDate) {
        Employee employee = new Employee(empId,assessmentType,assessmentName,assessmentDate);
        employeeRepository.save(employee);
    }

    @Override
    public Map<String,List<String>> findAllAssessments(Long username) {
        empId = username;
        return adminServiceClient.findAssessmentMap();
    }
}
