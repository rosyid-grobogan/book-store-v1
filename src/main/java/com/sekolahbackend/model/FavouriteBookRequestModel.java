package com.sekolahbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FavouriteBookRequestModel {
    @NotNull
    private Integer userId;
    @NotNull
    private Integer bookId;
}
