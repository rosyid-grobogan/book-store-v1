package com.sekolahbackend.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "book")
@Where(clause = "status = 'ACTIVE'")
public class Book extends Persistence {
	
	public enum BookStatus {
		FOR_SELL, OUT_OF_STOCK, HIDE
	}

	@Column(length = 255)
	private String title;
	
	@Column(length = 100)
	private String isbn;
	
	@Column(length = 255)
	private String authorName;
	
	@Column(columnDefinition = "text")
	private String synopsis;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date publicationDate;
	
	@Column(columnDefinition = "double precision default '0'")
	private Double price;
	
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private BookStatus bookStatus;
	
	@JoinColumn(name = "book_categori_id")
	@ManyToOne(targetEntity = BookCategory.class, fetch = FetchType.LAZY)
	private BookCategory bookCategory;

	@Where(clause = "status = 'ACTIVE'")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book", fetch = FetchType.LAZY)
	private Set<FavouriteBookDetail> favouriteBookDetails;
}
