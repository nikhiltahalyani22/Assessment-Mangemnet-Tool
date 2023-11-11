package io.nikks.adminservice.repository;

import io.nikks.adminservice.entity.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment,Long> {

    @Query("SELECT DISTINCT assessmentName FROM Assessment where assessmentType= :theAssessmentType")
    List<String> findByAssessmentType(String theAssessmentType);
    @Query("SELECT DISTINCT assessmentType FROM Assessment")
    List<String> findAllAssessmentType();
    Assessment findByAssessmentName(String theAssessmentName);
}
