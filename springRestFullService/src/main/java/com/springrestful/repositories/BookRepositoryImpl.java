package com.springrestful.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springrestful.domain.Book;

@Component
public class BookRepositoryImpl implements BookRepository {

	@Autowired
	EntityManager entityManager;

	@Override
	public Book findById(Integer id) {
		Book book= entityManager.find(Book.class, id);
		return book;
	}

	@Override
	public List<Book> findAll() {
		List<Book> listOfBook= entityManager.createQuery("from Book").getResultList();
		return listOfBook;
	}

	@Override
	public Book findByName(String name) {
		Book book= entityManager.createQuery("select b from Book b "
				+ "where b.name=:name",Book.class)
				.setParameter("name", name).getSingleResult();
		return book;
	}

	@Override
	public Optional<Book> save(Book book) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(book);
			entityManager.getTransaction().commit();
			return Optional.of(book);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<Book> getByNameNamedQuery(String name){
		Book book= entityManager.createNamedQuery("Book.findByName",Book.class)
		.setParameter("name", name).getSingleResult();
		return Optional.of(book);
	}
	
}
