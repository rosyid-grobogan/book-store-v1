package com.sekolahbackend.service;

import java.util.List;

public interface PersistenceService<T, ID> {

	T saveOrUpdate(T entity);

	T delete(T entity);

	T deleteById(ID id);

	T findById(ID id);

	List<T> findAll();
	
	Long countAll();
}
