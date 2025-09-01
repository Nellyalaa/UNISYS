package com.Uni.UNI_sys.domainrepo;

import com.Uni.UNI_sys.domain.Roles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Rolesrepo extends JpaRepository<Roles, Long> {
    Roles findByName(String name);
}
