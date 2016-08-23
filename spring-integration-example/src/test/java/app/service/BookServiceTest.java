package app.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import app.config.DbConfig;
import app.domain.Book;
import app.enums.BookType;
import app.service.impl.BookServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={DbConfig.class})
@Transactional
public class BookServiceTest {

  private static Logger logger = LoggerFactory.getLogger(BookServiceTest.class);
  
  @Autowired
  EntityManager entityManager;
  
  BookService bookService;
  
  @Before
  public void setUp() throws Exception {
    bookService = new BookServiceImpl(entityManager);
    Book book = new Book("a", "b", 200);
    book.setType(BookType.PSYCHOLOGY);
    assertThat(bookService.saveBook(book), is(greaterThan(0l)));
    logger.debug("saved a book: {}",book.toString());
  }

  @Test
  public void testBookQueryById(){
    Book book = bookService.getBookById(1);
    assertThat(book.getAuthor(), is("b"));
  }

}
