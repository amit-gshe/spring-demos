package app.service;

import app.domain.Book;

public interface BookService {

  long saveBook(Book book);
  
  Book getBookById(long id);
}
