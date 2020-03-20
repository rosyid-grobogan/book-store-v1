package com.sekolahbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import com.sekolahbackend.entity.BookCategory;
import com.sekolahbackend.entity.Persistence.Status;
import com.sekolahbackend.model.BookCategoryModel;
import com.sekolahbackend.repository.BookCategoryRepository;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {

	@Autowired
	private BookCategoryRepository bookCategoryRepository;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public BookCategoryModel saveOrUpdate(BookCategoryModel entity) {
		BookCategory bookCategory;
		if (entity.getId() != null) {
			bookCategory = bookCategoryRepository.findById(entity.getId()).orElse(null);
			if (bookCategory == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Book Category with id: " + entity.getId() + " not found");
			
			bookCategory.setCode(entity.getCode());
			bookCategory.setName(entity.getName());
			bookCategory = bookCategoryRepository.save(bookCategory);
		} else {
			bookCategory = new BookCategory();
			bookCategory.setCode(entity.getCode());
			bookCategory.setName(entity.getName());
			bookCategory = bookCategoryRepository.save(bookCategory);
		}
		BeanUtils.copyProperties(bookCategory, entity);
		return entity;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public BookCategoryModel delete(BookCategoryModel entity) {
		if (entity.getId() != null) {
			BookCategory bookCategory = bookCategoryRepository.findById(entity.getId()).orElse(null);
			if (bookCategory == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Book Category with id: " + entity.getId() + " not found");

			if (bookCategory.getBooks() != null && bookCategory.getBooks().size() > 0)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Book Category cannot be deleted because already used in Books");
			
			bookCategory.setStatus(Status.NOT_ACTIVE);
			bookCategory = bookCategoryRepository.save(bookCategory);
			BeanUtils.copyProperties(bookCategory, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public BookCategoryModel deleteById(Integer id) {
		if (id != null) {
			BookCategoryModel entity = new BookCategoryModel();
			BookCategory bookCategory = bookCategoryRepository.findById(id).orElse(null);
			if (bookCategory == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Book Category with id: " + id + " not found");
			
			if (bookCategory.getBooks() != null && bookCategory.getBooks().size() > 0)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Book Category cannot be deleted because already used in Books");
			
			bookCategory.setStatus(Status.NOT_ACTIVE);
			bookCategory = bookCategoryRepository.save(bookCategory);
			BeanUtils.copyProperties(bookCategory, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public BookCategoryModel findById(Integer id) {
		if (id != null) {
			BookCategoryModel entity = new BookCategoryModel();
			BookCategory bookCategory = bookCategoryRepository.findById(id).orElse(null);
			if (bookCategory == null)
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Book Category with id: " + id + " not found");
			
			BeanUtils.copyProperties(bookCategory, entity);
			return entity;
		} else
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Id cannot be null");
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<BookCategoryModel> findAll() {
		List<BookCategoryModel> entities = new ArrayList<>();
		bookCategoryRepository.findAll().forEach(data -> {
			BookCategoryModel entity = new BookCategoryModel();
			BeanUtils.copyProperties(data, entity);
			entities.add(entity);
		});
		return entities;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Long countAll() {
		return bookCategoryRepository.count();
	}
}