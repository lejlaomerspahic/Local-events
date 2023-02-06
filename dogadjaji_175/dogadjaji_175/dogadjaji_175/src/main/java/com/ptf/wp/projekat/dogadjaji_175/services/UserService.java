package com.ptf.wp.projekat.dogadjaji_175.services;

import com.ptf.wp.projekat.dogadjaji_175.models.User;
import com.ptf.wp.projekat.dogadjaji_175.viewmodels.UserRegistration;
import jakarta.validation.Valid;

public interface UserService {
	boolean userExist(String email);
	User save(@Valid UserRegistration registration);


}
