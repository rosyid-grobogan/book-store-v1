package com.sekolahbackend.controller;

import com.sekolahbackend.model.FavouriteBookModel;
import com.sekolahbackend.model.FavouriteBookRequestModel;
import com.sekolahbackend.service.FavouriteBookService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Api
@RestController
@RequestMapping("/api/rest/favourite-book")
public class FavouriteBookRestController {

    @Autowired
    private FavouriteBookService favouriteBookService;
    @PostMapping("/saveOrUpdate")
    public FavouriteBookModel saveOrUpdate(@RequestBody @Valid FavouriteBookRequestModel request, BindingResult result, HttpServletResponse response) throws IOException {

        FavouriteBookModel favouriteBookModel = new FavouriteBookModel();

        if (result.hasErrors()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(),
                    result.getAllErrors().toString());
            return favouriteBookModel;
        } else
            return favouriteBookService.saveOrUpdate(request);
    }

    @DeleteMapping("/deleteByFavouriteBookDetailId/{detailId}")
    public FavouriteBookModel delete(@PathVariable("detailId") final Integer detailId) {

        return favouriteBookService.deleteByFavouriteBookDetailId(detailId);
    }

    @GetMapping("/findAll")
    public List<FavouriteBookModel> findAll() {

        return favouriteBookService.findAll();
    }

    @GetMapping("/findById/{id}")
    public FavouriteBookModel findById(@PathVariable("id") final Integer
                                               id) {
        return favouriteBookService.findById(id);
    }

    @GetMapping("/findByUserId/{userId}")
    public FavouriteBookModel findByUserId(@PathVariable("userId") final Integer userId) {

        return favouriteBookService.findByUserId(userId);
    }
}