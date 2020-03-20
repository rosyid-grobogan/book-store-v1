package com.sekolahbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sekolahbackend.entity.BookCategory;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {

}
