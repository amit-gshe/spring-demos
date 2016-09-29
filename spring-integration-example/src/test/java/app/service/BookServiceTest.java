package app.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import app.config.DevDbConfig;
import app.domain.Book;
import app.enums.BookType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DevDbConfig.class})
@Sql("classpath:test-book-data.sql")
@Transactional
@ActiveProfiles("dev")
public class BookServiceTest {

  private static Logger logger = LoggerFactory.getLogger(BookServiceTest.class);
  
  @Autowired
  BookRepo bookService;

  @Test
  public void testSave(){
    Book book = new Book("a", "b", 200);
    book.setType(BookType.PSYCHOLOGY);
    assertThat(bookService.save(book).getId(), greaterThan(0l));
    logger.debug("saved a book: {}",book.toString());
    
  }

  @Test
  public void testFind(){
    Book book = bookService.findOne(1l);
    assertThat(book.getName(), is("双城记"));
  }
  
}
