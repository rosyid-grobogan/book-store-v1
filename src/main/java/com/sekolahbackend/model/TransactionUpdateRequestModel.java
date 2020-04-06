package com.sekolahbackend.model;

import com.sekolahbackend.entity.Transaction;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TransactionUpdateRequestModel {

    @NotNull
    private Integer transactionId;

    @NotNull
    private Transaction.TransactionStatus transactionStatus;
    private String receiptImageUrl;
}
