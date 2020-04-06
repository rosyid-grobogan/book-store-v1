package com.sekolahbackend.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartRequestModel {

    @NotNull
    private Integer userId;

    @NotNull
    private Integer bookId;

}
