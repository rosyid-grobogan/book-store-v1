package com.sekolahbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel extends PersistenceModel {
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
}