package com.bezkoder.spring.security.login.controllers;

import com.bezkoder.spring.security.login.models.Project;
import com.bezkoder.spring.security.login.payload.request.SignupRequest;
import com.bezkoder.spring.security.login.payload.response.MessageResponse;
import com.bezkoder.spring.security.login.repository.ProjectRepository;
import com.bezkoder.spring.security.login.repository.UserRepository;
import com.bezkoder.spring.security.login.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired private ProjectService projectService;

    // Save operation 
    @PostMapping("/Projects")
    public Project saveProject(
             @RequestBody Project Project)
    {
        return projectService.saveProject(Project);
    }

    // Read operation 
    @GetMapping("/Projects")
    public List<Project> fetchProjectList()
    {
        return projectService.fetchProjectList();
    }

    // Update operation 
    @PutMapping("/Projects/{id}")
    public Project
    updateProject(@RequestBody Project Project,
                     @PathVariable("id") Long ProjectId)
    {
        return projectService.updateProject(
                Project, ProjectId);
    }

    // Delete operation 
    @DeleteMapping("/Projects/{id}")
    public String deleteProjectById(@PathVariable("id")
                                               Long ProjectId)
    {
        projectService.deleteProjectById(
                ProjectId);

        return "Deleted Successfully";
    }


}
