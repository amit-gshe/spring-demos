package app.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import app.config.DbConfig;
import app.domain.Reader;
import app.enums.Gender;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes={DbConfig.class})
@Transactional
public class ReaderServiceTest {

  @Autowired
  ReaderService readerService;
  
  @Before
  public void setUp() throws Exception {
    Reader reader = new Reader("Lily", Gender.FEMALE, 21);
    reader = readerService.save(reader);
    assertThat(reader.getId(), greaterThan(0l));
  }

  @Test
  public void testQueryByIdAndShouldNotReturnNull() {
    Reader reader = readerService.findOne(1l);
    assertThat(reader, is(notNullValue()));
  }

}
