package com.librarymanagement.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.librarymanagement.entity.Book;
import com.librarymanagement.exception.BookIdNotFoundException;
import com.librarymanagement.repository.BookRepository;
import com.librarymanagement.srevice.BookService;


@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	BookRepository brepo;

	@Override
	public Book addBook(Book book) {
		return brepo.save(book);
	}

	@Override
	public Book getBookDetails(int bid) {
		return brepo.findById(bid).
			       orElseThrow(()-> new BookIdNotFoundException("Book Id is not correct"));
	}

	@Override
	public Book updateBookDetails(Book book, int bid) {
		Book updatedBook = brepo.findById(bid).
		         orElseThrow(()-> new BookIdNotFoundException("Book Id is not correct"));

		//set new values
		updatedBook.setBprice(book.getBprice());
		
		brepo.save(updatedBook); //saving updated details 
			return updatedBook;
	}

	@Override
	public void deleteBookDetail(int bid) {
		brepo.findById(bid).
		orElseThrow(()-> new BookIdNotFoundException("Book Id is not correct"));
	    brepo.deleteById(bid);
		
	}

	

}
