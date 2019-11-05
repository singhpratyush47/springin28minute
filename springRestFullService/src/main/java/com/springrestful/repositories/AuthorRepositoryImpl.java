package com.springrestful.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springrestful.domain.Author;

@Component
public class AuthorRepositoryImpl implements AuthorRepository{

	@Autowired
	private EntityManager entityManager;
    
    @Override
    public Author findById(Integer id) {
        Author author = entityManager.find(Author.class, id);
        return author;
    }
    public List<Author> findAll() {
        return entityManager.createQuery("select a from Author a").getResultList();
    }
    @Override
    public Author findByName(String name) {
        Author author = entityManager.createNamedQuery("Author.findByName", Author.class)
                .setParameter("name", name)
                .getSingleResult();
        return author;
    }
    public Optional<Author> save(Author author) {
        try {
            entityManager.persist(author);
            return Optional.of(author);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
