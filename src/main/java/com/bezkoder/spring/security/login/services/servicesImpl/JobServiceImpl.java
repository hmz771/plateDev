package com.bezkoder.spring.security.login.services.servicesImpl;

import com.bezkoder.spring.security.login.models.Job;
import com.bezkoder.spring.security.login.models.Job;
import com.bezkoder.spring.security.login.repository.JobRepository;
import com.bezkoder.spring.security.login.repository.JobRepository;
import com.bezkoder.spring.security.login.services.JobService;
import com.bezkoder.spring.security.login.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service

// Class
class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    // Save operation
    @Override
    public Job saveJob(Job job)
    {
        return jobRepository.save(job);
    }

    // Read operation
    @Override public List<Job> fetchJobList()
    {
        return (List<Job>)
                jobRepository.findAll();
    }

    // Update operation
    @Override
    public Job updateJob(Job job, Long JobId)
    {
        Job depDB
                = jobRepository.findById(JobId)
                .get();

        if (Objects.nonNull(job.getName())
                && !"".equalsIgnoreCase(
                job.getName())) {
            depDB.setName(
                    job.getName());
        }


        return jobRepository.save(depDB);
    }

    // Delete operation
    @Override
    public void deleteJobById(Long JobId)
    {
        jobRepository.deleteById(JobId);
    }
}
