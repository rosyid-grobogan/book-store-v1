package com.sekolahbackend.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookRequestUpdateModel extends BookRequestCreateModel {

	@NotNull
	private Integer id;
}
