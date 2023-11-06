package com.bezkoder.spring.security.login.controllers;

import com.bezkoder.spring.security.login.models.Job;
import com.bezkoder.spring.security.login.models.Project;
import com.bezkoder.spring.security.login.payload.response.MessageResponse;
import com.bezkoder.spring.security.login.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api")
public class ProjectController {

    @Autowired private ProjectService projectService;

    // Save operation 
    @PostMapping("/Projects")
    public Project saveProject(
             @RequestBody Project Project)
    {

        projectService.saveProject(Project);
        projectService.createFolder(Project.getPath());
        return null;

    }
    @PostMapping("/Projects/{id}/Jobs")
    public Project saveProjectWithJobs(@PathVariable("id") Long ProjectId,
            @RequestBody List<Job> Jobs)
    {
       Project pp= projectService.fetchProjectListById(ProjectId);
       pp.setJobs(Jobs);


        return projectService.saveProject(pp);


    }


    // Read operation

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/Projects")
    public List<Project> fetchProjectList()
    {
        var tt= projectService.fetchProjectList();
        return tt;
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
    public ResponseEntity<?> deleteProjectById(@PathVariable("id")
                                               Long ProjectId)
    {
        projectService.deleteProjectById(
                ProjectId);

        return ResponseEntity.ok(new MessageResponse("project Deleted successfully!"));
    }
    @GetMapping("/Projects/")
    public List<Project> findProjectByName(@RequestParam String name)
    {
        return projectService.fetchProjectListByName(
                name);



    }
    @GetMapping("/Project/{id}")
    public Project findProjectById(@PathVariable("id")
                                               Long ProjectId)
    {
        return projectService.fetchProjectListById(
                ProjectId);



    }

}
