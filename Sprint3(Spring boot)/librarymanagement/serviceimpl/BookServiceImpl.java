package com.librarymanagement.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.librarymanagement.entity.Book;
import com.librarymanagement.exception.BookIdNotFoundException;
import com.librarymanagement.repository.BookRepository;
import com.librarymanagement.srevice.BookService;

public class BookServiceImpl implements BookService{

	@Autowired
	BookRepository brepo;
	
	
	@Override
	public Book addBook(Book book) {
		return brepo.save(book);
	}

	@Override
	public Book getBookDetail(int bid) {
		return brepo.findById(bid).orElseThrow(()-> new BookIdNotFoundException("Book id is incorrect"));
	}

	@Override
	public void deleteBookDetail(int bid) {
		brepo.findById(bid).orElseThrow(()-> new BookIdNotFoundException("Book id is incorrect"));
		brepo.deleteById(bid);
		
	}

	@Override
	public Book borrowBook(Long bid, Long uid) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
