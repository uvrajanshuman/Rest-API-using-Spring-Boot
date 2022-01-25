package com.example.rest_api_demo.repositories;

import com.example.rest_api_demo.model.Book;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Integer> {
    
}
