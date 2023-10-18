package com.bezkoder.spring.security.login.services;

import com.bezkoder.spring.security.login.models.Tutorial;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface TutorialService {

    // Save operation
    Tutorial saveTutorial(Tutorial Tutorial);

    // Read operation
    List<Tutorial> fetchTutorialList();

    // Update operation
    Tutorial updateTutorial(Tutorial Tutorial,
                  Long TutorialId);

    // Delete operation
    void deleteTutorialById(Long TutorialId);




}
