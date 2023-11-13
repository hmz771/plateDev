package com.bezkoder.spring.security.login.services;

import com.bezkoder.spring.security.login.models.Project;
import com.bezkoder.spring.security.login.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {

    // Save operation
    Project saveProject(Project Project);

    // Read operation
    List<Project> fetchProjectList(User user);
    List<Project> fetchProjectListByName(String ProjectName);

    // Update operation
    Project updateProject(Project Project,
                                Long ProjectId);

    // Delete operation
    void deleteProjectById(Long ProjectId);


    Project fetchProjectListById(Long id);
    void createFolder(Project pth);
}
