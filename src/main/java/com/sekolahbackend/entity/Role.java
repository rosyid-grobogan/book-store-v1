package com.sekolahbackend.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "role")
public class Role {
    public enum RoleName {
        ROLE_ADMIN, ROLE_USER
    }
    @Id
    @NotNull
    @Size(min = 0, max = 50)
    @Column(name = "role_name", length = 50)
    private String roleName;

}
