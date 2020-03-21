package com.sekolahbackend.service;

import static com.sekolahbackend.util.FavouriteBookModelMapper.constructModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sekolahbackend.entity.*;
import com.sekolahbackend.repository.FavouriteBookRepository;
import com.sekolahbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import com.sekolahbackend.entity.Persistence.Status;
import com.sekolahbackend.model.FavouriteBookModel;
import com.sekolahbackend.model.FavouriteBookRequestModel;
import com.sekolahbackend.repository.BookRepository;
import com.sekolahbackend.repository.FavouriteBookDetailRepository;
import com.sekolahbackend.repository.FavouriteBookRepository;

@Service
public class FavouriteBookServiceImpl implements FavouriteBookService {
    @Autowired
    private FavouriteBookRepository favouriteBookRepository;
    @Autowired
    private FavouriteBookDetailRepository favouriteBookDetailRepository;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public FavouriteBookModel saveOrUpdate(FavouriteBookModel entity) {
        return null;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public FavouriteBookModel saveOrUpdate(FavouriteBookRequestModel
                                                   request) {
// validate user
        User user =
                userRepository.findById(request.getUserId()).orElse(null);
        if (user == null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST,
                    "User with id: " + request.getUserId() + " not found");
        FavouriteBook favouriteBook = favouriteBookRepository.findByUserId(user.getId());
        Set<FavouriteBookDetail> favouriteBookDetails = new HashSet<>();
// initialize
        if (favouriteBook == null) {
            favouriteBook = new FavouriteBook();
            favouriteBook.setUser(user);
            favouriteBook = favouriteBookRepository.save(favouriteBook);
// validate book
            Book book = bookRepository.findById(request.getBookId()).orElse(null);
            if (book == null)
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST,
                        "Book with id: " + request.getBookId() + " not found");
// detail
            favouriteBookDetails.add(saveFavouriteBookDetail(favouriteBook,
                    book));
        } else {
// update
            Book book =
                    bookRepository.findById(request.getBookId()).orElse(null);
            if (book == null)
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST,
                        "Book with id: " + request.getBookId() + " not found");
            List<FavouriteBookDetail> currentFavouriteBookDetails =
                    favouriteBookDetailRepository.findByUserIdAndBookId(user.getId(),
                            book.getId());
            if (currentFavouriteBookDetails == null ||
                    currentFavouriteBookDetails.size() == 0)
                favouriteBookDetails.add(saveFavouriteBookDetail(favouriteBook,
                        book));
        }
        favouriteBook.setFavouriteBookDetails(favouriteBookDetails);
        return constructModel(favouriteBook);
    }

    private FavouriteBookDetail saveFavouriteBookDetail(FavouriteBook favouriteBook, Book book) {
        FavouriteBookDetail favouriteBookDetail = new
                FavouriteBookDetail();
        favouriteBookDetail.setBook(book);
        favouriteBookDetail.setFavouriteBook(favouriteBook);
        return favouriteBookDetailRepository.save(favouriteBookDetail);
    }

    @Override
    public FavouriteBookModel delete(FavouriteBookModel entity) {
        return null;
    }
    @Override
    public FavouriteBookModel deleteById(Integer id) {
        return null;
    }
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public FavouriteBookModel deleteByFavouriteBookDetailId(Integer detailId) {
        FavouriteBookDetail favouriteBookDetail = (FavouriteBookDetail) favouriteBookDetailRepository.findById(detailId).orElse(null);
        if (favouriteBookDetail == null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST,
                    "Favourite Book Detail with id: " + detailId + " not found");
        favouriteBookDetail.setStatus(Persistence.Status.NOT_ACTIVE);
        favouriteBookDetail = favouriteBookDetailRepository.save(favouriteBookDetail);
        return constructModel(favouriteBookDetail.getFavouriteBook());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public FavouriteBookModel findById(Integer id) {
        return constructModel(favouriteBookRepository.findById(id).orElse(null));
    }
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<FavouriteBookModel> findAll() {
        return constructModel(favouriteBookRepository.findAll());
    }
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Long countAll() {
        return favouriteBookRepository.count();
    }
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public FavouriteBookModel findByUserId(Integer userId) {
        return (FavouriteBookModel) constructModel(favouriteBookRepository.findByUserId(userId));
    }
}