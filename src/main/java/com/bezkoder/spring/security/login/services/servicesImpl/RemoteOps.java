package com.bezkoder.spring.security.login.services.servicesImpl;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Vector;

public class RemoteOps {
    private String remoteHost;
    private Integer remotePort;
    private String remoteUserName;
    private String remoteUserPassword;
    private Session currentSession = null;

    //Constructor which uses default credentials and remote-host
    public RemoteOps(String remoteHost){
        this.remoteHost = remoteHost;
        //this.remotePort = //use default port
                this.remoteUserName ="pc6"; //use default username
                        this.remoteUserPassword ="1234"; //use default password
    }

    //Constructor which uses user-defined input credentials and
    // remote-host
    public RemoteOps(String remoteHost,
                     Integer remotePort,
                     String remoteUserName,
                     String remoteUserPassword){
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
        this.remoteUserName = remoteUserName;
        this.remoteUserPassword = remoteUserPassword;
    }

    //Primary method that establishes the connection
    // with the host and opens a session.
    public void createConn(){
        try {
            JSch jschConn = new JSch();
            System.out.println("############################0");
            currentSession = jschConn
                    .getSession(remoteUserName,remoteHost,remotePort);
            currentSession.setPassword(remoteUserPassword);
            System.out.println("############################1");
            Properties configProps = new Properties();
            //configProps.put("StrictHostKeyChecking","no");
            currentSession.setConfig(configProps);
            currentSession.connect();
            System.out.println("############################");
        } catch (JSchException j) {
            j=j;
            System.out.println("myerroris   "+ j);
            //Report your exception however you want to
        }
    }

    //other instance variables as defined in the above code snippet
    private Channel currentChannel = null;
    private ChannelSftp currentSFTPChannel = null;

    public void createDirectory(String destinationDir,
                                ChannelSftp sftpc){
       // var fff="/hhhh".split(File.separator);
        createConn();
        openSFTPChannel();
       // destinationDir=this.currentSFTPChannel.pwd()+"/hhhh";
        SftpATTRS dirAttributes = null;
        try {
            System.out.println("############################10");
            dirAttributes = this.currentSFTPChannel
                    .stat("pp");
            System.out.println("############################11   " +this.currentSFTPChannel   .pwd());
        } catch (SftpException s){
            System.out.println("############################888  "+s);
            //Directory does not exist.
            //Report it in your preferred manner.
        }

        try {
            destinationDir="pp";
            if(dirAttributes != null && dirAttributes.isDir()){
                // Directory exists.
            } else {
                Boolean rootPath = false;
                if(destinationDir.startsWith("/")){
                    rootPath = true;
                }
                String[] folders = destinationDir
                        .split("/");
                if(rootPath){
                    folders[0] = "/"+folders[0];
                }

                for(String eachFolder : folders){
                    if(eachFolder.length() > 0  && !eachFolder.contains(".")){
                        try{
                            sftpc.cd(eachFolder);
                        } catch (SftpException s){

//                             mkdirs(sftpc,"pp");


                            sftpc.mkdir(eachFolder);
                            dirAttributes = sftpc.stat(eachFolder);

                            /////////////






                            ////////////


                            if(dirAttributes.isDir()){
                                // Directory Created
                                sftpc.cd(eachFolder);
                            }
                        }
                    }
                }
            }
        } catch (Exception se){
            se=se;
            //Report the exception however you want to
        } finally {
            closeSFTPChannel();
        }
    }
    public  void mkdirs() {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession("Mounir-Laptop", "169.254.93.216", 22);
            session.setPassword("Mounir35");
            session.setConfig("StrictHostKeyChecking", "no"); // For testing, use proper host key checking in production
            session.connect();


            ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();

            String remoteFolderPath = "/ppp";
            channel.mkdir(remoteFolderPath);

            channel.disconnect();
            } catch (JSchException ex) {
            ex.printStackTrace();
        } catch (SftpException ex) {
            ex.printStackTrace();
        }

    }
    // Opening the channels
    public void openSFTPChannel(){
        try {
            this.currentChannel=currentSession.openChannel("sftp");
            System.out.println("############################5");
            this.currentChannel.connect();
            System.out.println("############################6");
            currentSFTPChannel = (ChannelSftp)currentChannel;
            System.out.println("############################7");
        } catch (JSchException j){
            //Report the exception however you want to
            System.out.println("############################0000");
        }
    }

    // Closes the SFTP Channel along with the
    // current session channel.
    public void closeSFTPChannel(){
        if(currentSFTPChannel != null){
            if(currentSFTPChannel.isConnected()){
                currentSFTPChannel.disconnect();
            }
        }
        closeChannel();
    }

    // Closes the Session Current Channel
    void closeChannel(){
        if(currentChannel != null){
            if(!currentChannel.isClosed()){
                this.currentChannel.disconnect();
            }
        }
    }
    //In continuation to the above class with the instance variables
    // defined & the opening and closing methods for channels.

    //Primary method for copying file from local to remote path
    public void copy(String destinationDir,
                     String fileName){

        SftpATTRS fileAttritbues = null;
        try{
            openSFTPChannel();

            createDirectory(destinationDir,currentSFTPChannel);
            currentSFTPChannel.cd(destinationDir);

            File f = new File(fileName);
            currentSFTPChannel
                    .put(new FileInputStream(f), f.getName());
            String newFilename =
                    Paths.get(destinationDir, fileName).toString();
            try {
                fileAttritbues =
                        currentSFTPChannel.stat(newFilename);
            } catch (SftpException s1){
                // File not copied
            }
        } catch(FileNotFoundException fn){
            //Report the exception however you want to.
        } catch(SftpException s){
            //Report the exception however you want to.
        } finally {
            closeSFTPChannel();
        }

    }
    //Primary method for deleting a remote file.
    public void delete(String file){

        SftpATTRS attributes=null;
        try{
            openSFTPChannel();
            attributes = currentSFTPChannel.stat(file);
        } catch (SftpException s){
            closeSFTPChannel();
            return;
        }

        try{
            currentSFTPChannel.rm(file);
        } catch (SftpException s){
            //Report the exception however you want to.
        }

        attributes=null;
        try{
            attributes = currentSFTPChannel.stat(file);
        } catch(SftpException s){
            //File deleted successfully
        }
        if(attributes != null){
            //File still exists after deletion
        }
        closeSFTPChannel();
    }
    //Closes the active session
    public void closeConn(){
        if(currentSession != null){
            if(currentSession.isConnected()){
                this.currentSession.disconnect();
            }
        }
    }
}