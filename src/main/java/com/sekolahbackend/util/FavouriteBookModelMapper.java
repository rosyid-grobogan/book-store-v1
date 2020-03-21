package com.sekolahbackend.util;

import com.sekolahbackend.entity.FavouriteBook;
import com.sekolahbackend.model.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavouriteBookModelMapper {
    public static FavouriteBookModel constructModel(FavouriteBook favouriteBook) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(favouriteBook.getUser(), userModel);
        FavouriteBookModel model = new FavouriteBookModel();
        model.setUserModel(userModel);
        List<FavouriteBookModel.DetailModel> details = new ArrayList<>();
        favouriteBook.getFavouriteBookDetails().forEach(data -> {
            FavouriteBookModel.DetailModel detail = new FavouriteBookModel.DetailModel();
            BookModel bookModel = new BookModel();
            BookCategoryModel bookCategoryModel = new BookCategoryModel();
            BeanUtils.copyProperties(data.getBook().getBookCategory(),
                    bookCategoryModel);
            bookModel.setBookCategory(bookCategoryModel);
            BeanUtils.copyProperties(data.getBook(), bookModel);
            detail.setBookModel(bookModel);
            BeanUtils.copyProperties(data, detail);
            details.add(detail);
        });
        model.setDetails(details);
        BeanUtils.copyProperties(favouriteBook, model);
        return model;
    }
    public static List<FavouriteBookModel>
    constructModel(List<FavouriteBook> favouriteBooks) {
        List<FavouriteBookModel> models = new ArrayList<>();
        favouriteBooks.forEach(favouriteBook -> {
            FavouriteBookModel model = constructModel(favouriteBook);
            models.add(model);
        });
        return models;
    }
    public static FavouriteBookModel
    construcModelForRequest(FavouriteBookRequestModel request) {
        FavouriteBookModel favouriteBookModel = new FavouriteBookModel();
        FavouriteBookModel.DetailModel detail =
                getFavouriteDetailModel(request.getBookId());
        favouriteBookModel.setUserModel(getUserModel(request.getUserId()));
        favouriteBookModel.setDetails(Arrays.asList(detail));
        return favouriteBookModel;
    }
    private static UserModel getUserModel(Integer userId) {
        UserModel userModel = new UserModel();
        userModel.setId(userId);
        return userModel;
    }
    private static FavouriteBookModel.DetailModel
    getFavouriteDetailModel(Integer bookId) {
        FavouriteBookModel.DetailModel detail = new
                FavouriteBookModel.DetailModel();
        BookModel bookModel = new BookModel();
        bookModel.setId(bookId);
        detail.setBookModel(bookModel);
        return detail;
    }
}