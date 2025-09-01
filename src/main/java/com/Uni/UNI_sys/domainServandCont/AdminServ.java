package com.Uni.UNI_sys.domainServandCont;

import com.Uni.UNI_sys.domain.Roles;
import com.Uni.UNI_sys.domain.Admin;
import com.Uni.UNI_sys.domainrepo.Adminrepo;
import com.Uni.UNI_sys.domainrepo.Rolesrepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AdminServ implements UserDetailsService {

    private final Adminrepo adminrepo;
    private final Rolesrepo rolesrepo;
    private final PasswordEncoder passwordEncoder;

    public Admin saveAdmin(Admin admin) {
        log.info("Saving new admin {} to the database", admin.getName());
        admin.setPass(passwordEncoder.encode(admin.getPass()));
        return adminrepo.save(admin);
    }

    public Roles saveRole(Roles role) {
        log.info("Saving new role {} to the database", role.getName());
        return rolesrepo.save(role);
    }

    public void addRoleToAdmin(String username, String rolename) {
        log.info("Adding role {} to admin {}", rolename, username);
        Admin admin = adminrepo.findByUsername(username);
        Roles role = rolesrepo.findByName(rolename);
        if (admin != null && role != null) {
            admin.getRole().add(role);
        } else {
            log.warn("Admin or Role not found: username={}, role={}", username, rolename);
        }
    }

    public Admin getAdmin(String username) {
        log.info("Fetching admin {}", username);
        return adminrepo.findByUsername(username);
    }

    public List<Admin> getAllAdmins() {
        log.info("Fetching all admins");
        return adminrepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminrepo.findByUsername(username);
        if (admin == null) {
            log.error("Admin not found in the database: {}", username);
            throw new UsernameNotFoundException("Admin not found in the database");
        } else {
            log.info("Admin {} found in the database", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        admin.getRole().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPass(), authorities);
    }
}
