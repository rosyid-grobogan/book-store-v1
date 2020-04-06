package com.sekolahbackend.service;

import com.sekolahbackend.model.UserModel;
import com.sekolahbackend.model.UserRequestModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService {

    UserModel register(UserRequestModel requestModel);
}
