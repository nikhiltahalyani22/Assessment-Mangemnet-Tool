package io.nikks.employeeservice.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "admin-service" ,url = "http://localhost:9090/api/assessment")
public interface AdminServiceClient {

    @GetMapping("/assessmentMap")
    Map<String, List<String>> findAssessmentMap();
}
