package com.bezkoder.spring.security.login.controllers;

import com.bezkoder.spring.security.login.models.Job;
import com.bezkoder.spring.security.login.payload.response.MessageResponse;
import com.bezkoder.spring.security.login.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api")
public class JobController {

    @Autowired private JobService jobService;

    // Save operation 
    @PostMapping("/Jobs")
    public Job saveJob(
            @RequestBody Job Job)
    {



        return jobService.saveJob(Job);

    }

//    @PostMapping("/Project/Jobs")
//    public Job saveJob(
//            @RequestBody Job Job)
//    {
//
//
//
//        return jobService.saveJob(Job);
//
//    }







//    @PostMapping("/tutorials/{tutorialId}/comments")
//    public ResponseEntity<Job> createComment(@PathVariable(value = "tutorialId") Long projectId,
//                                                 @RequestBody Job jobRequest) {
//        Job comment = jobService.fetchJobListById(projectId).map(project -> {
//            jobRequest.setProject(project);
//            return jobService.saveJob(jobRequest);
//        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));
//
//        return new ResponseEntity<>(comment, HttpStatus.CREATED);
//    }
    // Read operation

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/Jobs")
    public List<Job> fetchJobList()
    {
        return jobService.fetchJobList();
    }

    // Update operation 
    @PutMapping("/Jobs/{id}")
    public Job
    updateJob(@RequestBody Job Job,
                  @PathVariable("id") Long JobId)
    {
        return jobService.updateJob(
                Job, JobId);
    }

    // Delete operation 
    @DeleteMapping("/Jobs/{id}")
    public ResponseEntity<?> deleteJobById(@PathVariable("id")
                                            Long JobId)
    {
        jobService.deleteJobById(
                JobId);

        return ResponseEntity.ok(new MessageResponse("job Deleted successfully!"));
    }
    @GetMapping("/Jobs/")
    public List<Job> findJobByName(@RequestParam String name)
    {
        return jobService.fetchJobListByName(
                name);



    }
    @GetMapping("/Job/{id}")
    public Job findJobById(@PathVariable("id")
                                           Long JobId)
    {
        return jobService.fetchJobListById(
                JobId);



    }

}
