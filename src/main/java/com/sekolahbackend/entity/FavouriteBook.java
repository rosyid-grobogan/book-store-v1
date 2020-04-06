package com.sekolahbackend.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "favourite_book")
@Where(clause = "status = 'ACTIVE'")
public class FavouriteBook extends Persistence {

    private static final long serialVersionUID = 1L;
//    private static final long serialVersionUID = -1079959320014409414L;

    @JoinColumn(name = "user_id")
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;

    @Where(clause = "status = 'ACTIVE'")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "favouriteBook", fetch = FetchType.LAZY)
    private Set<FavouriteBookDetail> favouriteBookDetails;
}