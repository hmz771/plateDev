package com.bezkoder.spring.security.login.controllers;

import com.bezkoder.spring.security.login.models.Job;
import com.bezkoder.spring.security.login.models.Parameter;
import com.bezkoder.spring.security.login.models.Project;
import com.bezkoder.spring.security.login.models.User;
import com.bezkoder.spring.security.login.payload.response.MessageResponse;
import com.bezkoder.spring.security.login.repository.ParameterRepository;
import com.bezkoder.spring.security.login.repository.UserRepository;
import com.bezkoder.spring.security.login.security.services.UserDetailsImpl;
import com.bezkoder.spring.security.login.security.services.UserDetailsServiceImpl;
import com.bezkoder.spring.security.login.services.ParameterService;
import com.bezkoder.spring.security.login.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class ProjectController {

    @Autowired  private ProjectService projectService;
    @Autowired private ParameterService parameterService;
    @Autowired private UserRepository userRepository;
    private User getCurrentUser()
    {   String userName = SecurityContextHolder.getContext().getAuthentication().getName();



        //**************************

        Optional<User> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isPresent()) {
            return (userOptional.get());


            // Now, userId contains the ID of the user
        } else {
            // Handle the case where the user is not found
            return null;
        }

    }
    // add operation
    @PostMapping("/Projects")
    public Project saveProject(
             @RequestBody Project Project)
    {

            Project.setUser( getCurrentUser());




        projectService.saveProject(Project);
        //
        projectService.createFolder(Project);
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

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/Projects")
    public List<Project> fetchProjectList()
    {
      var ee=  getCurrentUser();
        var tt= projectService.fetchProjectList(ee);
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
    @GetMapping("/Parameters/{id}")
    public Project findParameterByName(@PathVariable("id")
                                           Long ProjectId)
    {
        return projectService.fetchProjectListById(
                ProjectId);



    }
    @GetMapping("/Projects/Params")
    public Parameter findParameterByName(@RequestParam String name)
    {
        var el=parameterService.fetchParameterListByName(name);
        if(!el.isEmpty())
        return parameterService.fetchParameterListByName(name).get(0);
        else
            return null;



    }

}
