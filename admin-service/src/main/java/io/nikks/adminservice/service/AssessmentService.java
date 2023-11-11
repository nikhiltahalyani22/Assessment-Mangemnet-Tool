package io.nikks.adminservice.service;

import io.nikks.adminservice.entity.Assessment;
import io.nikks.adminservice.entity.Employee;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface AssessmentService {
    void addAssessment(String assessmentType , @RequestParam String assessmentName);

    List<Assessment> findAll();

    Map<String, List<String>> findAssessmentMap();

    List<Employee> findAllScheduledAssessments();

    String deleteByAssessmentName(String assessmentName);
}
