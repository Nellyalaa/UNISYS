package com.Uni.UNI_sys.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aid;
    private String name;
    private String username;
    private String pass;

    @ManyToMany(fetch = EAGER)
    private Collection<Roles> role = new ArrayList<>();


    // Getters and setters

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
       return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Collection<Roles> getRole() {
        return role;
    }

    public void setRole(Collection<Roles> role) {
        this.role = role;
    }


    // UserDetails implementation

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.stream()
                .map(r -> (GrantedAuthority) () -> r.getName())
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
