package com.bezkoder.spring.security.login.services;

import com.bezkoder.spring.security.login.models.Job;
import org.springframework.stereotype.Service;

import java.util.List;


    @Service
    public interface JobService {

        // Save operation
        Job saveJob(Job Job);

        // Read operation
        List<Job> fetchJobList();

        // Update operation
        Job updateJob(Job Job,
                              Long JobId);

        // Delete operation
        void deleteJobById(Long JobId);


        List<Job> fetchJobListByName(String name);

        Job fetchJobListById(Long jobId);
    }
