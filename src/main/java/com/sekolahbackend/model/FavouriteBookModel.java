package com.sekolahbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FavouriteBookModel extends PersistenceModel {
    private UserModel userModel;
    private List<DetailModel> details;
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DetailModel extends PersistenceModel {
        private BookModel bookModel;
    }
}