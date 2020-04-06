package com.sekolahbackend.service;

import static com.sekolahbackend.util.TransactionModelMapper.constructModel;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import com.sekolahbackend.entity.CartDetail;
import com.sekolahbackend.entity.CartDetail.CartDetailStatus;
import com.sekolahbackend.entity.Persistence.Status;
import com.sekolahbackend.entity.Transaction;
import com.sekolahbackend.entity.Transaction.PaymentMethod;
import com.sekolahbackend.entity.Transaction.TransactionStatus;
import com.sekolahbackend.entity.TransactionDetail;
import com.sekolahbackend.entity.User;
import com.sekolahbackend.model.TransactionCreateRequestModel;
import com.sekolahbackend.model.TransactionModel;
import com.sekolahbackend.model.TransactionUpdateRequestModel;
import com.sekolahbackend.repository.CartDetailRepository;
import com.sekolahbackend.repository.TransactionDetailRepository;
import com.sekolahbackend.repository.TransactionRepository;
import com.sekolahbackend.repository.UserRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    @Override
    public TransactionModel saveOrUpdate(TransactionModel entity) {
        return null;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public TransactionModel save(TransactionCreateRequestModel request) {
        // validate user
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User with id: " + request.getUserId() + " not found");

        // validate cart detail
        List<CartDetail> cartDetails = cartDetailRepository.findByIds(request.getCartDetailIds());
        Set<TransactionDetail> transactionDetails = new HashSet<>();
        if (cartDetails == null || cartDetails.size() == 0)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Cart details not found");

        Transaction transaction = new Transaction();
        transaction.setInvoiceNumber(UUID.randomUUID().toString());
        transaction.setPaymentMethod(PaymentMethod.BANK_TRANSFER); // if many options, it should be from request
        transaction.setStatus(Status.ACTIVE);
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setUser(user);
        transaction = transactionRepository.save(transaction);

        // detail
        for (CartDetail data : cartDetails) {
            TransactionDetail transactionDetail = new TransactionDetail();
            transactionDetail.setBook(data.getBook());
            transactionDetail.setCartDetail(data);
            transactionDetail.setPrice(data.getBook().getPrice());
            transactionDetail.setTransaction(transaction);
            transactionDetail = transactionDetailRepository.save(transactionDetail);
            transactionDetails.add(transactionDetail);

            // update cart detail
            data.setCartDetailStatus(CartDetailStatus.TRANSACTED);
            cartDetailRepository.save(data);
        }
        transaction.setTransactionDetails(transactionDetails);
        return constructModel(transaction);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public TransactionModel update(TransactionUpdateRequestModel request) {
        Transaction transaction = transactionRepository.findById(request.getTransactionId()).orElse(null);
        if (transaction == null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Transaction with id: " + request.getTransactionId() + " not found");

        if (!transaction.getTransactionStatus().equals(TransactionStatus.SETTLED)) {
            if (StringUtils.isNotBlank(request.getReceiptImageUrl()))
                transaction.setReceiptImageUrl(request.getReceiptImageUrl());
            if (request.getTransactionStatus().equals(TransactionStatus.SETTLED))
                transaction.setPaymentTime(new Date());
            transaction.setTransactionStatus(request.getTransactionStatus());
            transaction = transactionRepository.save(transaction);
        } else
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Transaction with id: " + transaction.getId() + " already SETTLED");
        return constructModel(transaction);
    }

    @Override
    public TransactionModel delete(TransactionModel entity) {
        return null;
    }

    @Override
    public TransactionModel deleteById(Integer id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public TransactionModel findById(Integer id) {
        return constructModel(transactionRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<TransactionModel> findAll() {
        return constructModel(transactionRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Long countAll() {
        return transactionRepository.count();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<TransactionModel> findByUserId(Integer userId) {
        return constructModel(transactionRepository.findByUserId(userId));
    }

}

