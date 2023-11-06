package com.bezkoder.spring.security.login.services.servicesImpl;

import com.bezkoder.spring.security.login.models.Parameter;
import com.bezkoder.spring.security.login.models.Project;
import com.bezkoder.spring.security.login.repository.JobRepository;
import com.bezkoder.spring.security.login.repository.ParameterRepository;
import com.bezkoder.spring.security.login.repository.ProjectRepository;
import com.bezkoder.spring.security.login.services.ProjectService;
import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Objects;

@Service

// Class
class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // Save operation
    @Override
    public Project saveProject(Project Project) {
        return projectRepository.save(Project);
    }

    // Read operation
    @Override
    public List<Project> fetchProjectList() {
        return (List<Project>)
                projectRepository.findAll();
    }

    @Override
    public List<Project> fetchProjectListByName(String ProjectName) {
        return (List<Project>)
                projectRepository.findByNameContaining(ProjectName).get();
    }

    // Update operation
    @Override
    public Project
    updateProject(Project Project,
                  Long ProjectId) {
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
    public void deleteProjectById(Long ProjectId) {
        projectRepository.deleteById(ProjectId);
    }

    @Override
    public Project fetchProjectListById(Long id) {
        return (Project)
                projectRepository.findById(id).get();
    }

    @Override
    public void createFolder(String pth) {

//String projectPath="C:\\Users\\HomeNet\\Documents\\ggv\\yy";
//        File theDir = new File(projectPath);
//        if (!theDir.exists()){
//            theDir.mkdirs();
//        }
        CopyAllJobs(pth);

//
//        RemoteOps rp=new RemoteOps("169.254.93.216",22,"Mounir-Laptop","Mounir35");
//        //RemoteOps rp=new RemoteOps("172.16.200.28",22,"pc6","1234");
//        rp.mkdirs();
        //       rp.openSFTPChannel();
//        ChannelSftp sftpc=new ChannelSftp();
//        //rp. mkdirs("pp");
//
//        rp.createDirectory("D:/",sftpc);

    }
    @Autowired
    private ParameterRepository parameterRepository;
public void CopyAllJobs(String pth)
{
    Parameter param= parameterRepository.findByName("all_jobs").get();
    String sourceFolder = param.getPath();
    String targetFolder = pth+"\\Jobs";
    File theDir = new File(targetFolder);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
    File sFile = new File(sourceFolder);
    File[] sourceFiles = sFile.listFiles();
    for (File fSource : sourceFiles) {
        File fTarget = new File(new File(targetFolder), fSource.getName());
        copyFileUsingStream(fSource, fTarget);
       // deleteFiles(fSource);
    }}
    private static void copyFileUsingStream(File source, File dest) {
    InputStream is = null;
    OutputStream os = null;
    try {
        is = new FileInputStream(source);
        os = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
    } catch (Exception ex) {
        System.out.println("Unable to copy file:" + ex.getMessage());
    } finally {
        try {
            is.close();
            os.close();
        } catch (Exception ex) {
        }
    }
}


}







