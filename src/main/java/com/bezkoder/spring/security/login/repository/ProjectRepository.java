package com.bezkoder.spring.security.login.repository;

import com.bezkoder.spring.security.login.models.ERole;
import com.bezkoder.spring.security.login.models.Project;
import com.bezkoder.spring.security.login.models.Role;
import com.bezkoder.spring.security.login.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<List<Project>> findByNameContaining(String name);
    Optional<List<Project>> findByUser(User user);
    //Optional<Project> findByName(String name);
}
