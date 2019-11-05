package com.springrestful.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.springrestful.domain.Book;

@Repository
public interface BookRepository {

	Book findById(Integer id);
	List<Book> findAll();
	Book findByName(String name);
	Optional<Book> save(Book book);
}
