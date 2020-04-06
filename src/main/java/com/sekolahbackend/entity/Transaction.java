package com.sekolahbackend.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "transaction")
@Where(clause = "status = 'ACTIVE' ")
public class Transaction extends Persistence {

    private static final long serialVersionUID = 1L;
    //private static final long serialVersionUID = 9220882551539786922L;

    public enum TransactionStatus {
        PENDING, SETTLED, EXPIRED
    }

    public enum PaymentMethod {
        BANK_TRANSFER, CASH_ON_DELIVERY, VIRTUAL_ACCOUNT
    }

    @Column(unique = true)
    private String invoiceNumber;

    @Column(length = 255)
    private String receiptImageUrl;

    @JoinColumn(name = "user_id")
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User user;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date paymentTime;

    @Where(clause = "status = 'ACTIVE' ")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction", fetch = FetchType.LAZY)
    private Set<TransactionDetail> transactionDetails;
}
