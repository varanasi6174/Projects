package com.librarymanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.librarymanagement.entity.Book;
import com.librarymanagement.srevice.BookService;

import jakarta.validation.Valid;

@RestController
public class BookController {

	@Autowired
	BookService bs;
	
	@PostMapping("/addBook")
	public ResponseEntity<Book> saveBook(@Valid @RequestBody Book book){
		return new ResponseEntity<Book>(bs.addBook(book),
				HttpStatus.CREATED);
	}
	
	@GetMapping("/getbook/{bid}")
	public ResponseEntity<Book> getBook(@PathVariable("bid") int bid){
		return new ResponseEntity<Book>(bs.getBookDetail(bid),HttpStatus.OK);
	}
	
	@DeleteMapping("/removeBook/{bid}")
	public ResponseEntity<String> deleteBook(@PathVariable("bid") int bid){
		bs.deleteBookDetail(bid);
		return new ResponseEntity<String>("Deleted Successfully..", HttpStatus.OK);
	}
}
