package com.sekolahbackend.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sekolahbackend.model.CartModel;
import com.sekolahbackend.model.CartRequestModel;
import com.sekolahbackend.service.CartService;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping("/api/rest/cart")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/saveOrUpdate")
    public CartModel saveOrUpdate(@RequestBody @Valid CartRequestModel request, BindingResult result,
                                  HttpServletResponse response) throws IOException {
        CartModel CartModel = new CartModel();
        if (result.hasErrors()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), result.getAllErrors().toString());
            return CartModel;
        } else
            return cartService.saveOrUpdate(request);
    }

    @DeleteMapping("/deleteByCartDetailId/{detailId}")
    public CartModel delete(@PathVariable("detailId") final Integer detailId) {
        return cartService.deleteByCartDetailId(detailId);
    }

    @GetMapping("/findAll")
    public List<CartModel> findAll() {
        return cartService.findAll();
    }

    @GetMapping("/findById/{id}")
    public CartModel findById(@PathVariable("id") final Integer id) {
        return cartService.findById(id);
    }

    @GetMapping("/findByUserId/{userId}")
    public CartModel findByUserId(@PathVariable("userId") final Integer userId) {
        return cartService.findByUserId(userId);
    }

}
