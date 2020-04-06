package com.sekolahbackend.repository;

import com.sekolahbackend.entity.Cart;
import com.sekolahbackend.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {

    @Query("FROM CartDetail detail WHERE detail.cart.user.id = ?1 AND detail.book.id = ?2 AND detail.cartDetailStatus = ?3")
    List<CartDetail> findByUserIdAndBookIdAndDetailStatus(Integer userId, Integer bookId, CartDetail.CartDetailStatus status);

    @Query("FROM CartDetail detail WHERE detail.id IN ?1")
    List<CartDetail> findByIds(Set<Integer> ids);
}
