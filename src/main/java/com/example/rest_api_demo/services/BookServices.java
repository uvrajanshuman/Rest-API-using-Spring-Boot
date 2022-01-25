package com.example.rest_api_demo.services;

import java.util.List;

import com.example.rest_api_demo.exceptions.ResourceNotFoundException;
import com.example.rest_api_demo.model.Book;

public interface BookServices {
    /* To Populate the H2 db wit initial data */
    void populatedb();
    /* To get all books data */
    List<Book> getAllBooks() throws ResourceNotFoundException;
    /* To get book data of provided id */
    Book getBookById(int id) throws ResourceNotFoundException;
    /* To add a book into db */
    Book addBook(Book book);
    /* To delete book of provided id */
    Book deleteBook(int bookId) throws ResourceNotFoundException;
    /* To update book of provided id*/
    Book updateBook(int bookId, Book newBook) throws ResourceNotFoundException;
}
