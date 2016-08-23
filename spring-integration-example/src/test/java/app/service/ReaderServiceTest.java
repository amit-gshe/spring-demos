package app.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import app.config.DbConfig;
import app.domain.QReader;
import app.domain.Reader;
import app.enums.Gender;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={DbConfig.class})
@Transactional
public class ReaderServiceTest {

  @Autowired
  ReaderService readerService;
  
  @Autowired
  EntityManager entityManager;
  
  @Before
  public void setUp() throws Exception {
    Reader reader = new Reader("Lily", Gender.FEMALE, 21);
    reader = readerService.save(reader);
    assertThat(reader.getId(), greaterThan(0l));
  }

  @Test
  public void testQueryByIdAndShouldNotReturnNull() {
    Reader reader = readerService.findByName("Lily");
    assertThat(reader, is(notNullValue()));
  }
  
  @Test
  public void testJpaAndQueryDsl(){
    JPAQuery<?> query = new JPAQuery<Reader>(entityManager);
    QReader qReader = QReader.reader;
    Reader reader = query.select(qReader).from(qReader).where(qReader.name.eq("Lily")).fetchOne();
    assertThat(reader, notNullValue());
  }

  @Test
  public void testWithPredicate(){
    QReader qReader = QReader.reader;
    Predicate predicate = qReader.name.eq("Lily");
    Reader reader = readerService.findOne(predicate);
    assertThat(reader, notNullValue());
  }
  
}
