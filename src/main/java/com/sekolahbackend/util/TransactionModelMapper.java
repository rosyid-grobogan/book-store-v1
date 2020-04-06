package com.sekolahbackend.util;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.sekolahbackend.entity.Transaction;
import com.sekolahbackend.model.BookCategoryModel;
import com.sekolahbackend.model.BookModel;
import com.sekolahbackend.model.TransactionModel;
import com.sekolahbackend.model.UserModel;

public class TransactionModelMapper {

    public static TransactionModel constructModel(Transaction transaction) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(transaction.getUser(), userModel);

        TransactionModel model = new TransactionModel();
        model.setUserModel(userModel);

        List<TransactionModel.DetailModel> details = new ArrayList<>();
        transaction.getTransactionDetails().forEach(data -> {
            TransactionModel.DetailModel detail = new TransactionModel.DetailModel();
            BookModel bookModel = new BookModel();
            BookCategoryModel bookCategoryModel = new BookCategoryModel();

            BeanUtils.copyProperties(data.getBook().getBookCategory(), bookCategoryModel);
            bookModel.setBookCategory(bookCategoryModel);

            BeanUtils.copyProperties(data.getBook(), bookModel);
            detail.setBookModel(bookModel);

            BeanUtils.copyProperties(data, detail);
            details.add(detail);
        });
        model.setDetails(details);

        BeanUtils.copyProperties(transaction, model);
        return model;
    }

    public static List<TransactionModel> constructModel(List<Transaction> transactions) {
        List<TransactionModel> models = new ArrayList<>();
        transactions.forEach(transaction -> {
            TransactionModel model = constructModel(transaction);
            models.add(model);
        });
        return models;
    }
}
