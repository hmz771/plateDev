package com.bezkoder.spring.security.login.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;
    private String path;

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
    @OneToMany(
            cascade = CascadeType.ALL,fetch = FetchType.LAZY    )
    @JoinColumn(name = "project_id")

    private List<Job> jobs = new ArrayList<>();
    //***********************



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @ManyToOne(
            fetch = FetchType.LAZY    )
    @JsonIgnore
    @JoinColumn(name = "user_id")

    private User user = new User();
///***********
public List<DtaObject> getDta() {
   return dtaObject;
}
//
//    public void setDtaObject(List<DtaObject> dtaObjects) {
//        this.dtaObject = dtaObjects;
//    }
//
@Transient
    private List<DtaObject> dtaObject = new ArrayList<>();
    //***********************
}
