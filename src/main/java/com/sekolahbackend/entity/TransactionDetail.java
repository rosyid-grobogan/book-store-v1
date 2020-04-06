package com.sekolahbackend.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "transaction_detail")
@Where(clause = "status = 'ACTIVE' ")
public class TransactionDetail extends Persistence{

    private static final long serialVersionUID = 1L;
//    private static final long serialVersionUID = -1695120839903033361L;

    @JoinColumn(name = "book_id")
    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
    private Book book;

    @Column(columnDefinition = "double precision default '0' ")
    private Double price;

    @JoinColumn(name = "transaction_id")
    @ManyToOne(targetEntity = Transaction.class, fetch = FetchType.LAZY)
    private Transaction transaction;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_detail_id", referencedColumnName = "id")
    private CartDetail cartDetail;

}
