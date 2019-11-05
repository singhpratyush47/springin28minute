package com.springrestful.dataLoader;

import java.util.Iterator;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springrestful.domain.Author;
import com.springrestful.domain.Book;
import com.springrestful.repositories.AuthorRepository;
import com.springrestful.repositories.BookRepository;

@Component
public class CommandLineRun implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private AuthorRepository authorRepo;
	@Override
	@Transactional
	public void run(String... args) throws Exception {

	 	Author author = new Author("Author 1");
        author.addBook(new Book("Book 1"));
        author.addBook(new Book("Book 2"));
        author.addBook(new Book("Book 3"));
        Optional<Author> optionalAuthor= authorRepo.save(author);
        System.out.println("====Optional Author====");
        optionalAuthor.ifPresent(System.out::println);
        System.out.println("====Find ALL====");
        Iterator<Author> authorIterator= authorRepo.findAll().iterator();
        while(authorIterator.hasNext()) {
        	System.out.println("Books Written -->"+authorIterator.next().getBooks());
        }
        System.out.println("====Find By Name====");
        Author author1= authorRepo.findByName("Author 1");
        System.out.println();
        author1.addBook(new Book("Book 4"));
        Optional<Author> optionalAuthor1= authorRepo.save(author1);
        System.out.println("====Optional Author1====");
        optionalAuthor1.ifPresent(System.out::println);
	}

}
