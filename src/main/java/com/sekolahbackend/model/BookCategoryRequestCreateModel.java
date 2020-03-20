package com.sekolahbackend.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookCategoryRequestCreateModel {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String code;
	
}
