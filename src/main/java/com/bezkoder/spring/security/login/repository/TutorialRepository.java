package com.bezkoder.spring.security.login.repository;

import com.bezkoder.spring.security.login.models.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    Optional<Tutorial> findByTitle(String name);
    //Optional<Project> findByName(String name);
}