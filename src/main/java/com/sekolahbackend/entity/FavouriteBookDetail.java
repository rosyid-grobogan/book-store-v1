package com.sekolahbackend.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "favourite_book_detail")
@Where(clause = "status = 'ACTIVE'")
public class FavouriteBookDetail extends Persistence {

    private static final long serialVersionUID = 1L;
//    private static final long serialVersionUID = 1130010943968579177L;

    @JoinColumn(name = "book_id")
    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
    private Book book;

    @JoinColumn(name = "favourite_book_id")
    @ManyToOne(targetEntity = FavouriteBook.class, fetch = FetchType.LAZY)
    private FavouriteBook favouriteBook;
}