package com.sekolahbackend.repository;

import com.sekolahbackend.entity.FavouriteBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavouriteBookRepository extends JpaRepository<FavouriteBook, Integer> {
    FavouriteBook findByUserId(Integer userId);
}
