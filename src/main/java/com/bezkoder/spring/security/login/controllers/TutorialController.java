package com.bezkoder.spring.security.login.controllers;

import com.bezkoder.spring.security.login.models.Tutorial;
import com.bezkoder.spring.security.login.services.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;

    // Save operation 
    @PostMapping("/Tutorials")
    public Tutorial saveTutorial(
            @RequestBody Tutorial Tutorial)
    {
        return tutorialService.saveTutorial(Tutorial);
    }

    // Read operation 
    @GetMapping("/Tutorials")
    public List<Tutorial> fetchTutorialList()
    {
        return tutorialService.fetchTutorialList();
    }

    // Update operation 
    @PutMapping("/Tutorials/{id}")
    public Tutorial
    updateTutorial(@RequestBody Tutorial Tutorial,
              @PathVariable("id") Long TutorialId)
    {
        return tutorialService.updateTutorial(
                Tutorial, TutorialId);
    }

    // Delete operation 
    @DeleteMapping("/Tutorials/{id}")
    public String deleteTutorialById(@PathVariable("id")
                                        Long TutorialId)
    {
        tutorialService.deleteTutorialById(
                TutorialId);

        return "Deleted Successfully";
    }


}
