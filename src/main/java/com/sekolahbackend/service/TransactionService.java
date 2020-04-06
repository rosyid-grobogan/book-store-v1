package com.sekolahbackend.service;

import com.sekolahbackend.model.TransactionCreateRequestModel;
import com.sekolahbackend.model.TransactionModel;
import com.sekolahbackend.model.TransactionUpdateRequestModel;

import java.util.List;

public interface TransactionService extends PersistenceService<TransactionModel, Integer> {

    TransactionModel save(TransactionCreateRequestModel request);

    TransactionModel update(TransactionUpdateRequestModel request);

    List<TransactionModel> findByUserId(Integer userId);
}
