package com.sekolahbackend.model;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sekolahbackend.entity.Transaction.PaymentMethod;
import com.sekolahbackend.entity.Transaction.TransactionStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionModel extends PersistenceModel {

    private UserModel userModel;

    private String invoiceNumber;
    private String receiptImageUrl;

    private TransactionStatus transactionStatus;

    private PaymentMethod paymentMethod;

    private Date paymentTime;

    private List<DetailModel> details;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DetailModel extends PersistenceModel {

        private BookModel bookModel;

        private Double price;

    }
}

