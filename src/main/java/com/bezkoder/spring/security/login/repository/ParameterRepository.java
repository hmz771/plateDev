package com.bezkoder.spring.security.login.repository;

import com.bezkoder.spring.security.login.models.Job;
import com.bezkoder.spring.security.login.models.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    Optional<List<Parameter>> findByNameContaining(String name);
    Optional<Parameter> findByName(String name);
}
