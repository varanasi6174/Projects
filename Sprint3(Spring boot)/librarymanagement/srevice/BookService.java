package com.librarymanagement.srevice;

import com.librarymanagement.entity.Book;

public interface BookService {

	//method for saving Book details in db table
		Book addBook(Book book);
		
		//method to fetch Book detail based on bid from db table
		Book getBookDetail(int bid);
		
		
		//method to remove Book detail based on bid from db table
		void deleteBookDetail(int bid);
		
		//method to borrow Book based on bid and uid from db table
		  public Book borrowBook(Long bid, Long uid);

	

	
}
