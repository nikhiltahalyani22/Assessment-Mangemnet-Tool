package io.nikks.employeeservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.nikks.employeeservice.entity.Employee;
import io.nikks.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    Map<String, List<String>> mapMono;

    @GetMapping()
    public ResponseEntity<List<Employee>> findAllEmployee(){
        return new ResponseEntity<>(employeeService.findAllEmployee(),HttpStatus.OK);
    }
    @PostMapping("/scheduleAssessment")
    public ModelAndView scheduleAssessment(@RequestParam String assessmentType, @RequestParam String assessmentName, @RequestParam String assessmentDate){
        employeeService.scheduleAssessment(assessmentType,assessmentName,assessmentDate);
        return new ModelAndView("employee")
                .addObject("assessmentType", assessmentType)
                .addObject("assessmentName",assessmentName)
                .addObject("assessmentDate",assessmentDate)
                .addObject("assessmentMap",mapMono);
    }

    @GetMapping("/home")
    @CircuitBreaker(name = "admin-service", fallbackMethod = "findAssessmentMapFallbackMethod")
    public ModelAndView findAssessmentMap(@RequestParam(name = "username", required = false) Long username){
        mapMono=employeeService.findAllAssessments(username);
        if(mapMono!=null) {
            return new ModelAndView("employee").
                    addObject("assessmentMap",mapMono);
        }
        throw  new RuntimeException();
    }

    public ResponseEntity<?> findAssessmentMapFallbackMethod(Throwable t) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Admin Service is unavailable. Please try after sometime");
    }
}
