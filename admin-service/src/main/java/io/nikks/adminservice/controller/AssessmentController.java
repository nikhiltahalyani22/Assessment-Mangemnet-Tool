package io.nikks.adminservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.nikks.adminservice.entity.Assessment;
import io.nikks.adminservice.entity.Employee;
import io.nikks.adminservice.service.AssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/assessment")
@RequiredArgsConstructor
public class AssessmentController {

    private final AssessmentService assessmentService;


    @GetMapping("/addAssessment")
    public String addAssessmentForm() {
        return "assessmentForm";
    }
    @PostMapping("/addAssessment")
    public String addAssessment(@RequestParam String assessmentType , @RequestParam String assessmentName) {
        assessmentService.addAssessment(assessmentType,assessmentName);
        return "redirect:/api/assessment";
    }

    @GetMapping()
    public ModelAndView admin() throws RuntimeException {
        List<Assessment> assessments = assessmentService.findAll();
        return new ModelAndView("admin")
                .addObject("assessments", assessments);
    }

    @GetMapping("/assessmentMap")
    public ResponseEntity<Map<String,List<String>>> findAssessmentMap() {
        return new ResponseEntity<>(assessmentService.findAssessmentMap(), HttpStatus.OK);
    }


    @PostMapping("/{assessmentName}")
    public String deleteByAssessmentName(@PathVariable String assessmentName) {
        assessmentService.deleteByAssessmentName(assessmentName);
        return "redirect:/api/assessment";
    }


    @GetMapping("/allScheduledAssessments")
    @CircuitBreaker(name = "employee-service", fallbackMethod = "findAllScheduledAssessmentFallbackMethod")
    public ModelAndView findAllScheduledAssessment() {
        List<Employee> employees = assessmentService.findAllScheduledAssessments();
        if (employees.isEmpty()) {
            /**
             * Create custom exception
             */
            throw new RuntimeException("No Scheduled Assessment ");
        }
        return new ModelAndView("showAllAssessment")
                    .addObject("employees",employees);
    }

    public ResponseEntity<String> findAllScheduledAssessmentFallbackMethod(Throwable t) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Employee Service is unavailable. Please try after sometime");
    }


}