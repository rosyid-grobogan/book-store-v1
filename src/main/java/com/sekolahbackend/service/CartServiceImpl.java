package com.sekolahbackend.service;

import static com.sekolahbackend.util.CartModelMapper.constructModel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import com.sekolahbackend.entity.Book;
import com.sekolahbackend.entity.Cart;
import com.sekolahbackend.entity.CartDetail;
import com.sekolahbackend.entity.CartDetail.CartDetailStatus;
import com.sekolahbackend.entity.Persistence.Status;
import com.sekolahbackend.entity.User;
import com.sekolahbackend.model.CartModel;
import com.sekolahbackend.model.CartRequestModel;
import com.sekolahbackend.repository.BookRepository;
import com.sekolahbackend.repository.CartDetailRepository;
import com.sekolahbackend.repository.CartRepository;
import com.sekolahbackend.repository.UserRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CartModel saveOrUpdate(CartModel entity) {
        return null;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CartModel saveOrUpdate(CartRequestModel request) {
        // validate user
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User with id: " + request.getUserId() + " not found");

        Cart cart = cartRepository.findByUserId(user.getId());
        Set<CartDetail> cartDetails = new HashSet<>();
        // initialize
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);

            // validate book
            Book book = bookRepository.findById(request.getBookId()).orElse(null);
            if (book == null)
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Book with id: " + request.getBookId() + " not found");

            // detail
            cartDetails.add(saveCartDetail(cart, book));
        } else {
            // update
            Book book = bookRepository.findById(request.getBookId()).orElse(null);
            if (book == null)
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Book with id: " + request.getBookId() + " not found");

            List<CartDetail> currentCartDetails = cartDetailRepository.findByUserIdAndBookIdAndDetailStatus(user.getId(), book.getId(), CartDetailStatus.CARTED);
            if (currentCartDetails == null || currentCartDetails.size() == 0)
                cartDetails.add(saveCartDetail(cart, book));
        }
        cart.setCartDetails(cartDetails);
        return constructModel(cart);
    }

    private CartDetail saveCartDetail(Cart cart, Book book) {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setBook(book);
        cartDetail.setCart(cart);
        cartDetail.setCartDetailStatus(CartDetailStatus.CARTED);
        return cartDetailRepository.save(cartDetail);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CartModel deleteByCartDetailId(Integer detailId) {
        CartDetail cartDetail = cartDetailRepository.findById(detailId).orElse(null);
        if (cartDetail == null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Cart Detail with id: " + detailId + " not found");
        cartDetail.setStatus(Status.NOT_ACTIVE);
        cartDetail = cartDetailRepository.save(cartDetail);
        return constructModel(cartDetail.getCart());
    }

    @Override
    public CartModel delete(CartModel entity) {
        return null;
    }

    @Override
    public CartModel deleteById(Integer id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public CartModel findById(Integer id) {
        return constructModel(cartRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<CartModel> findAll() {
        return constructModel(cartRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Long countAll() {
        return cartRepository.count();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public CartModel findByUserId(Integer userId) {
        return constructModel(cartRepository.findByUserId(userId));
    }

}