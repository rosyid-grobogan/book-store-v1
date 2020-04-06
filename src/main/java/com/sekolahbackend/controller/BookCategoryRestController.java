package com.sekolahbackend.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sekolahbackend.model.BookCategoryModel;
import com.sekolahbackend.model.BookCategoryRequestCreateModel;
import com.sekolahbackend.model.BookCategoryRequestUpdateModel;
import com.sekolahbackend.service.BookCategoryService;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/api/rest/book-category")
public class BookCategoryRestController {
	
	@Autowired
	private BookCategoryService bookCategoryService;
	
	@PostMapping("/save")
	public BookCategoryModel save(@RequestBody @Valid BookCategoryRequestCreateModel request, BindingResult result, HttpServletResponse response) throws IOException {

		BookCategoryModel bookCategoryModel = new BookCategoryModel();

		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return bookCategoryModel;
		} else {
			BeanUtils.copyProperties(request, bookCategoryModel);
			return bookCategoryService.saveOrUpdate(bookCategoryModel);
		}
	}
	
	@PostMapping("/update")
	public BookCategoryModel update(@RequestBody @Valid BookCategoryRequestUpdateModel request, BindingResult result, HttpServletResponse response) throws IOException {

		BookCategoryModel bookCategoryModel = new BookCategoryModel();

		if (result.hasErrors()) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
			return bookCategoryModel;
		} else {
			BeanUtils.copyProperties(request, bookCategoryModel);
			return bookCategoryService.saveOrUpdate(bookCategoryModel);
		}
	}
	
	@DeleteMapping("/deleteById/{id}")
	public BookCategoryModel delete(@PathVariable("id") final Integer id) {
		return bookCategoryService.deleteById(id);
	}

	@GetMapping("/findAll")
	public List<BookCategoryModel> findAll() {
		return bookCategoryService.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public BookCategoryModel findById(@PathVariable("id") final Integer id) {
		return bookCategoryService.findById(id);
	}
}
