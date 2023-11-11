package io.nikks.loginservice.repository;

import io.nikks.loginservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<User,Long> {
}
