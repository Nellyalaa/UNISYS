package com.Uni.UNI_sys.domainrepo;

import com.Uni.UNI_sys.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Adminrepo extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}
