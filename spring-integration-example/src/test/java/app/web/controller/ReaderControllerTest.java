package app.web.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import app.Application;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
@ActiveProfiles("dev")
@Sql("classpath:test-reader-data.sql")
@Transactional
public class ReaderControllerTest {

  private static Logger logger = LoggerFactory.getLogger(BookControllerTest.class);

  @Autowired
  private WebApplicationContext wac;
  private MockMvc mockMvc;

  @Autowired
  ReaderController readerController;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }

  @Test
  public void testQueryReader() throws Exception {
    mockMvc.perform(get("/user/{id}", 1)).andExpect(status().isOk())
        .andExpect(jsonPath("registeredDate", equalTo("2016-08-23 18:00")));
  }

}
