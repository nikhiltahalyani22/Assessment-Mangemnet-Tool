package io.nikks.loginservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "login")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private Long empid;
    private String password;
    private String type;

}