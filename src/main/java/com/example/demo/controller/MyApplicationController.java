package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import com.example.demo.models.Book;
import com.example.demo.repository.BookRepository;
import com.google.common.collect.Lists;

@RestController
@CrossOrigin(origins = "*")
public class MyApplicationController{

    private final BookRepository bookRepository;
    
    @Autowired
    public MyApplicationController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping(path = "/", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> hello(){        
        JSONObject entity = new JSONObject();
        entity.put("message","hello");
        entity.put("status","success");
        return new ResponseEntity(entity,HttpStatus.OK);
    }

    @PostMapping("/save")
    public String saveBook(@RequestBody Book book){
        Book savedBook = this.bookRepository.save(book);
        return savedBook.toString();
    }

    @GetMapping("/save/{title}/{author}/{year}")
    public String saveBook(@PathVariable String title, @PathVariable String author, @PathVariable int year){
        Book savedBook = this.bookRepository.save(new Book(title, author, year));
        return savedBook.toString();
    }

    @GetMapping("/findAll")
    public String findAllBooks() {
        Iterable<Book> books = this.bookRepository.findAll();
        return Lists.newArrayList(books).toString();
    }

    @GetMapping("/findByAuthorName/{author}")
    public String findByAuthor(@PathVariable String author) {
        List<Book> books = this.bookRepository.findByAuthor(author);
        return books.toString();
    }

    @GetMapping("/findByYear/{year}")
    public String findByYearAfter(@PathVariable int year) {
        List<Book> books = this.bookRepository.findByYear(year);
        return books.toString();
    }

    @GetMapping("/findByAuthorNameYear/{year}")
    public String findByAuthorYear(@PathVariable String author,@PathVariable int year) {
        List<Book> books = this.bookRepository.findByAuthorAndYear(author, year);
        return books.toString();
    }

    @GetMapping("/deleteAll")
    public void removeAllBooks() {
        this.bookRepository.deleteAll();
    }
}