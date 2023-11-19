package com.bezkoder.spring.security.login;

import com.bezkoder.spring.security.login.repository.RoleRepository;
import com.bezkoder.spring.security.login.services.InitialServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication

public class SpringBootSecurityLoginApplication extends SpringBootServletInitializer {



	public static void main(String[] args) {

		SpringApplication.run(SpringBootSecurityLoginApplication.class, args);
        //initialServ.InitialServx();
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootSecurityLoginApplication.class);
    }
}
