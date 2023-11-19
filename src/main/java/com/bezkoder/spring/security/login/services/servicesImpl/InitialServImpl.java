package com.bezkoder.spring.security.login.services.servicesImpl;

import com.bezkoder.spring.security.login.models.ERole;
import com.bezkoder.spring.security.login.models.Role;
import com.bezkoder.spring.security.login.repository.RoleRepository;
import com.bezkoder.spring.security.login.services.InitialServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitialServImpl implements InitialServ {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void InitialServx()

    {
        var tt = roleRepository.findAll();
        Role userv = new Role(ERole.ROLE_XXX);
//        Role admin = new Role("Admin");
//        Role customer = new Role("Customer");


        roleRepository.save(userv);
        if (tt.isEmpty()) {


        }
    }
}
