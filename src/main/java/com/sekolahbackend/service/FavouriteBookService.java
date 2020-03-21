package com.sekolahbackend.service;

import com.sekolahbackend.model.FavouriteBookModel;
import com.sekolahbackend.model.FavouriteBookRequestModel;

public interface FavouriteBookService extends
        PersistenceService<FavouriteBookModel, Integer> {
    FavouriteBookModel saveOrUpdate(FavouriteBookRequestModel request);
    FavouriteBookModel findByUserId(Integer userId);
    FavouriteBookModel deleteByFavouriteBookDetailId(Integer detailId);
}