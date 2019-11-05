package com.springrestful.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.springrestful.domain.Author;

@Repository
public interface AuthorRepository {

	Author findById(Integer id);
	List<Author> findAll();
	Author findByName(String name);
	Optional<Author> save(Author author);
}
