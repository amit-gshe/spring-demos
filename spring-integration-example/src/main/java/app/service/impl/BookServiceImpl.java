package app.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import app.domain.Book;
import app.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {

  EntityManager entityManager;
  
  public BookServiceImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public long saveBook(Book book) {
    entityManager.persist(book);
    return book.getId();
  }

  @Override
  public Book getBookById(long id) {
    Query q = entityManager.createQuery("from Book where id=:id");
    q.setParameter("id",id);
    return (Book) q.getSingleResult();
  }
  
}
