package com.sekolahbackend.repository;

import com.sekolahbackend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByUserId(Integer userId);
}
