package io.nikks.adminservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "assessment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "atype")
    private String assessmentType;
    @Column(name = "aname")
    private String assessmentName;

    public Assessment(String assessmentType, String assessmentName) {
        this.assessmentType = assessmentType;
        this.assessmentName = assessmentName;
    }
}
