package com.springrestful.dataLoader;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.springrestful.domain.Author;
import com.springrestful.domain.Book;
import com.springrestful.repositories.AuthorRepository;
import com.springrestful.repositories.BookRepository;

//@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private AuthorRepository authorRepo;
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		 	Author author = new Author("Author 1");
	        author.addBook(new Book("Book 1"));
	        author.addBook(new Book("Book 2"));
	        author.addBook(new Book("Book 3"));
	        authorRepo.save(author);
	}
}
