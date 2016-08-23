package app.service;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import app.config.DbConfig;
import app.domain.QReader;
import app.domain.Reader;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={DbConfig.class})
@Sql("classpath:test-reader-data.sql")
@Transactional
public class ReaderServiceTest {

  @Autowired
  ReaderService readerService;
  
  @Autowired
  EntityManager entityManager;
  
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Test
  public void testQueryByIdAndShouldNotReturnNull() {    
    Reader reader = readerService.findByName("Lily");
    assertThat(reader, notNullValue());
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
