package io.nikks.adminservice.service;

import io.nikks.adminservice.entity.Assessment;
import io.nikks.adminservice.entity.Employee;
import io.nikks.adminservice.repository.AssessmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class Service implements AssessmentService{

    private final AssessmentRepository assessmentRepository;
    private final WebClient.Builder webClientBuilder;

    @Override
    public void addAssessment(String assessmentType , @RequestParam String assessmentName) {
        assessmentRepository.save(new Assessment(assessmentType,assessmentName));
    }

    @Override
    public List<Assessment> findAll() {
        return assessmentRepository.findAll();
    }
/*
        TODO
        For findByAssessmentType
        USE MAP KEY VALUE PAIR
        Try to create a object in future

        OLD CODE
        List<String> allAssessmentType = assessmentRepository.findAllAssessmentType();
        Map<String,List<String>> assessmentMap = new HashMap<>();
        for (String assessmentTypist: allAssessmentType) {
            List<String> assessmentNames = assessmentRepository.findByAssessmentType(assessmentTypist);
            assessmentMap.put(assessmentTypist,assessmentNames);
        }
        return assessmentMap;
 */
    @Override
    public Map<String, List<String>> findAssessmentMap() {
        List<String> allAssessmentType = assessmentRepository.findAllAssessmentType();
        if(allAssessmentType!=null){
            return allAssessmentType.stream()
                    .collect(Collectors.toMap(
                            assessmentType -> assessmentType,
                            assessmentRepository::findByAssessmentType));
        }
        throw new RuntimeException("No assessment type");
    }

    @Override
    public String deleteByAssessmentName(String assessmentName) {
        Assessment assessment = assessmentRepository.findByAssessmentName(assessmentName);
        if(assessment!=null){
            assessmentRepository.deleteById(assessment.getId());
            return "Assessment is deleted";
        }
        return null;
    }

     @Override
    public List<Employee> findAllScheduledAssessments(){
        return webClientBuilder.baseUrl("http://api-gateway")
                .build()
                .get()
                .uri("/api/employee")
                .retrieve()
                .bodyToFlux(Employee.class)
                .collectList()
                .block();
    }


}
