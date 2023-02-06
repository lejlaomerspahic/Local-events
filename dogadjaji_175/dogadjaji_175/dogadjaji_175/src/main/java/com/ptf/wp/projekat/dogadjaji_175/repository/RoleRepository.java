package com.ptf.wp.projekat.dogadjaji_175.repository;

import com.ptf.wp.projekat.dogadjaji_175.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
