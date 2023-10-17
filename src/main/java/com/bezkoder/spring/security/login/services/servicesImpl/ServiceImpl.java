package com.bezkoder.spring.security.login.services.servicesImpl;

import com.bezkoder.spring.security.login.models.Project;
import com.bezkoder.spring.security.login.repository.ProjectRepository;
import com.bezkoder.spring.security.login.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service

// Class
class ProjectServiceImpl implements ProjectService {

        @Autowired
        private ProjectRepository projectRepository;

        // Save operation
        @Override
        public Project saveProject(Project Project)
        {
            return projectRepository.save(Project);
        }

        // Read operation
        @Override public List<Project> fetchProjectList()
        {
            return (List<Project>)
                    projectRepository.findAll();
        }

        // Update operation
        @Override
        public Project
        updateProject(Project Project,
                         Long ProjectId)
        {
            Project depDB
                    = projectRepository.findById(ProjectId)
                    .get();

            if (Objects.nonNull(Project.getName())
                    && !"".equalsIgnoreCase(
                    Project.getName())) {
                depDB.setName(
                        Project.getName());
            }


            return projectRepository.save(depDB);
        }

        // Delete operation
        @Override
        public void deleteProjectById(Long ProjectId)
        {
            projectRepository.deleteById(ProjectId);
        }
}
