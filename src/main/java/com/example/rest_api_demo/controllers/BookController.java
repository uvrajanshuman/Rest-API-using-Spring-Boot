package com.example.rest_api_demo.controllers;

import java.util.List;

import com.example.rest_api_demo.exceptions.ResourceNotFoundException;
import com.example.rest_api_demo.model.Book;
import com.example.rest_api_demo.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    BookServices bookServices;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        // The book object would automatically be converted into json using the jackson
        // dependency.
        // jackson is automatically configured in spring boot and it keep on converting
        // the json data into object for request json,
        // and response object into json
        try {
            List<Book> bookList = bookServices.getAllBooks();
            return ResponseEntity.status(HttpStatus.OK).body(bookList);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int Id){
        try{
            Book optional =bookServices.getBookById(Id);
            return ResponseEntity.status(HttpStatus.OK).body(optional);
        }catch(ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        // @RequestBody will typecast the request json body into book object.
        try{
            Book addedBook = bookServices.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedBook);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> removeBook(@PathVariable("id") int bookId){
        try{
            Book book = bookServices.deleteBook(bookId);
            return ResponseEntity.status(HttpStatus.OK).body(book);
        }catch(ResourceNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") int bookId, @RequestBody Book newBook){
        try{
            Book book = bookServices.updateBook(bookId, newBook);
            return ResponseEntity.status(HttpStatus.OK).body(book);
        }catch(ResourceNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } 
    }
}
