package com.sekolahbackend.repository;

import com.sekolahbackend.entity.FavouriteBookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavouriteBookDetailRepository extends JpaRepository<FavouriteBookDetail, Integer> {
    @Query("FROM FavouriteBookDetail detail WHERE detail.favouriteBook.user.id = ?1 AND detail.book.id = ?2")
    List<FavouriteBookDetail> findByUserIdAndBookId(Integer userId,
                                                    Integer bookId);
}
