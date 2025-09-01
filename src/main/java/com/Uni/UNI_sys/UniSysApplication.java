package com.Uni.UNI_sys;

import com.Uni.UNI_sys.domain.Roles;
import com.Uni.UNI_sys.domain.Admin;
import com.Uni.UNI_sys.domainServandCont.AdminServ;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UniSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniSysApplication.class, args);
	}

    @Bean
    CommandLineRunner run(AdminServ adminserv) {
        return args -> {


            adminserv.saveRole(new Roles(null, "ROLE_ADMIN"));
            adminserv.saveRole(new Roles(null, "VIEW_STUDENT"));
            adminserv.saveRole(new Roles(null, "EDIT_STUDENT"));
            adminserv.saveRole(new Roles(null, "DELETE_STUDENT"));


            Admin admin1 = adminserv.saveAdmin(new Admin(
                    null, "Nelly", "Nelly1", "pass", new ArrayList<>()));

            Admin admin2 = adminserv.saveAdmin(new Admin(
                    null, "Rawan", "R11", "pass123", new ArrayList<>()));

            Admin admin3 = adminserv.saveAdmin(new Admin(
                    null, "Ahmed", "AE", "aaee", new ArrayList<>()));


            adminserv.addRoleToAdmin("Nelly1", "ROLE_ADMIN");
            adminserv.addRoleToAdmin("R11", "ROLE_ADMIN");
            adminserv.addRoleToAdmin("AE", "ROLE_ADMIN");


            adminserv.addRoleToAdmin("Nelly1", "VIEW_STUDENT");
            adminserv.addRoleToAdmin("Nelly1", "EDIT_STUDENT");
            adminserv.addRoleToAdmin("Nelly1", "DELETE_STUDENT");
        };
    }

}



