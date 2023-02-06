package com.ptf.wp.projekat.dogadjaji_175.repository;

import com.ptf.wp.projekat.dogadjaji_175.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);



}
