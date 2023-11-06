package com.bezkoder.spring.security.login.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;






    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "parameters")
    public class Parameter {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long id;

        private String name;
        private String path;
}
