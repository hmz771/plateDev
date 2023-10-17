package com.bezkoder.spring.security.login.repository;

import com.bezkoder.spring.security.login.models.ERole;
import com.bezkoder.spring.security.login.models.Project;
import com.bezkoder.spring.security.login.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByName(String name);
    //Optional<Project> findByName(String name);
}
