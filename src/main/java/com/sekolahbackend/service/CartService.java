package com.sekolahbackend.service;

import com.sekolahbackend.model.CartModel;
import com.sekolahbackend.model.CartRequestModel;

public interface CartService extends PersistenceService<CartModel, Integer> {

    CartModel saveOrUpdate(CartRequestModel request);

    CartModel findByUserId(Integer userId);

    CartModel deleteByCartDetailId(Integer detailId);
}

