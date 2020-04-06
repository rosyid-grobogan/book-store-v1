package com.sekolahbackend.controller;

import com.sekolahbackend.model.TransactionCreateRequestModel;
import com.sekolahbackend.model.TransactionModel;
import com.sekolahbackend.service.TransactionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Api
@RestController
@RequestMapping("/api/rest/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/checkout")
    public TransactionModel checkout(@RequestBody @Valid TransactionCreateRequestModel request, BindingResult result, HttpServletResponse response) throws IOException {
        TransactionModel transactionModel = new TransactionModel();
        if (result.hasErrors()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(),
                    result.getAllErrors().toString());
            return transactionModel;
        } else
            return transactionService.save(request);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/findAll")
    public List<TransactionModel> findAll() {
        return transactionService.findAll();
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/findById/{id}")
    public TransactionModel findById(@PathVariable("id") final Integer
                                             id) {
        return transactionService.findById(id);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/findByUserId/{userId}")
    public List<TransactionModel> findByUserId(@PathVariable("userId")
                                               final Integer userId) {
        return transactionService.findByUserId(userId);
    }
}
