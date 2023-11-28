package com.bezkoder.spring.security.login.services.servicesImpl;

import com.bezkoder.spring.security.login.models.*;
import com.bezkoder.spring.security.login.repository.JobRepository;
import com.bezkoder.spring.security.login.repository.ParameterRepository;
import com.bezkoder.spring.security.login.repository.ProjectRepository;
import com.bezkoder.spring.security.login.services.ProjectService;
import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;
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
        var ooo=parameterRepository.findByName("root_projects").get().getPath();
        Project.setPath(ooo);
        return projectRepository.save(Project);
    }

    // Read operation
    @Override
    public List<Project> fetchProjectList(User user) {
        //return (List<Project>)
             var ii=   projectRepository.findByUser(user).get();

        for (Project var : ii)
        {

            if(var.getPath()!=null &&var.getPath()!="" )
            {
                fillFiles(var);
            }

        }
        return ii;
    }
    private void fillFiles(Project Pr)
    {
        try {
            //Fill jobs
            var st = Pr.getPath() +"\\"+ Pr.getName()+"\\Jobs";
            var stDta = Pr.getPath() +"\\"+ Pr.getName()+"\\Jobs\\data";
            ArrayList<Job> JobsList = new ArrayList<Job>();
            ArrayList<DtaObject> DtaList = new ArrayList<DtaObject>();
            File dir = new File(st);
            File dirDta = new File(stDta);
            String[] list = dir.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".ipynb");
                }
            });
            for (String fl : list) {
                var jb = new Job();
                jb.setName(fl);jb.setPath("http://localhost:9999/notebooks/"+Pr.getName()+"/Jobs/"+fl);
                JobsList.add(jb);

            }
            Pr.setJobs(JobsList);
            //******
            String[] listDta = dir.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".*");
                }
            });
            for (String fl : listDta) {
                var dtaObject = new DtaObject();
                dtaObject.setName(fl);
                DtaList.add(dtaObject);

            }
            Pr.setDtaObject(DtaList);
            //******
        }catch (Exception ex)
        {
            System.out.println(ex.toString());


        }
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
    public void createFolder(Project prj)  {

//String projectPath="C:\\Users\\HomeNet\\Documents\\ggv\\yy";
//        File theDir = new File(projectPath);
//        if (!theDir.exists()){
//            theDir.mkdirs();
//        }
        CopyAllJobs(prj);

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
public void CopyAllJobs(Project pr)  {
    Parameter param= parameterRepository.findByName("all_jobs").get();
    String sourceFolder = param.getPath();
    //*****
    String targetFolderPrj = pr.getPath()+"\\"+pr.getName();
    var ll=Paths.get(targetFolderPrj);


    File theDirPrj = new File(ll.toString());
    if (!theDirPrj.exists()){
        theDirPrj.mkdirs();
    }
    try
    {
        DosFileAttributes dosAttributes = Files.readAttributes(Paths.get(targetFolderPrj), DosFileAttributes.class);

        // Set the hidden attribute
        Files.setAttribute(Paths.get(targetFolderPrj), "dos:hidden", true);
    } catch (IOException e)
    {
        e.printStackTrace();
    }
    //*****
    String targetFolderDtaS = pr.getPath()+"\\"+pr.getName()+"\\Jobs\\data\\Seismic";
    File theDirDtaS = new File(targetFolderDtaS);
    if (!theDirDtaS.exists()){
        theDirDtaS.mkdirs();
    }
    String targetFolderDtaW = pr.getPath()+"\\"+pr.getName()+"\\Jobs\\data\\Well";
    File theDirDtaW = new File(targetFolderDtaW);
    if (!theDirDtaW.exists()){
        theDirDtaW.mkdirs();
    }

    //******
    String targetFolder = pr.getPath()+"\\"+pr.getName()+"\\Jobs";
//    File theDir = new File(targetFolder);
//        if (!theDir.exists()){
//            theDir.mkdirs();
//        }
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







