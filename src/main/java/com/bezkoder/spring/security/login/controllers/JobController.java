package com.bezkoder.spring.security.login.controllers;

import com.bezkoder.spring.security.login.models.Job;
import com.bezkoder.spring.security.login.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    // Read operation 
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
    public String deleteJobById(@PathVariable("id")
                                            Long JobId)
    {
        jobService.deleteJobById(
                JobId);

        return "Deleted Successfully";
    }


}
