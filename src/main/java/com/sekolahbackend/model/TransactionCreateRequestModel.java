package com.sekolahbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionCreateRequestModel {

    @NotNull
    private Integer userId;

    @NotNull
    private Set<Integer> cartDetailIds;
}
