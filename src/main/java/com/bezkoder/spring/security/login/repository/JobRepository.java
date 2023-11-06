package com.bezkoder.spring.security.login.repository;

import com.bezkoder.spring.security.login.models.ERole;
import com.bezkoder.spring.security.login.models.Job;
import com.bezkoder.spring.security.login.models.Project;
import com.bezkoder.spring.security.login.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<List<Job>> findByNameContaining(String name);
}
