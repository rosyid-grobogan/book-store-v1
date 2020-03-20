package com.sekolahbackend.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sekolahbackend.entity.Book;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookRequestCreateModel {

	@NotBlank
	private String title;
	
	@NotBlank
	private String isbn;
	
	@NotBlank
	private String authorName;
	
	@NotBlank
	private String synopsis;
	
	@NotNull
	private Date publicationDate;
	
	@NotNull
	private Double price;
	
	@NotNull
	private Integer bookCategoryId;
	
	@NotNull
	private Book.BookStatus bookStatus;
}
