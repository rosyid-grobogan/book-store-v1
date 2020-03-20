package com.sekolahbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sekolahbackend.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
