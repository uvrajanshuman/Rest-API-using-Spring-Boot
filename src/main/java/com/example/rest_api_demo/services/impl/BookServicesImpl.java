package com.example.rest_api_demo.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import com.example.rest_api_demo.exceptions.ResourceNotFoundException;
import com.example.rest_api_demo.model.Book;
import com.example.rest_api_demo.repositories.BookRepository;
import com.example.rest_api_demo.services.BookServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServicesImpl implements BookServices {
    @Autowired
    BookRepository bookRepo;

    public void populatedb() {
        List<Book> list = Arrays.asList(
                new Book(1, "Java", "abc"),
                new Book(2, "C", "def"),
                new Book(3, "Python", "ghi"),
                new Book(4, "Kotlin", "jkl"),
                new Book(5, "DS", "mno")
        // ,new Book(6,"Algo","pqr"),
        // new Book(7,"OS","stu"),
        // new Book(8,"DBMS","vwx"),
        // new Book(9,"JavaScript","yza")
        // new Book(10,"C#","bcd")
        );
        bookRepo.saveAll(list);
    }

    public List<Book> getAllBooks() throws ResourceNotFoundException {
        List<Book> bookList = (List<Book>) bookRepo.findAll();
        if (bookList.isEmpty()) {
            throw new ResourceNotFoundException("Books", "Books", "Books");
        }
        return bookList;
    }

    public Book getBookById(int id) throws ResourceNotFoundException {
        try {
            return bookRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Books", "id", id);
        }
    }

    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    public Book deleteBook(int bookId) throws ResourceNotFoundException {
        try {
            Book book = bookRepo.findById(bookId).get();
            bookRepo.delete(book);
            return book;
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Books", "id", bookId);
        }
    }

    public Book updateBook(int bookId, Book newBook) throws ResourceNotFoundException {
        try {
            Book oldBook = bookRepo.findById(bookId).get();
            oldBook.setAuthor(newBook.getAuthor());
            oldBook.setTitle(newBook.getTitle());
            return bookRepo.save(oldBook);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Books", "id", bookId);
        }
    }
}
