package app.cucumber;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import app.config.DevDbConfig;
import app.domain.Reader;
import app.service.ReaderService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DevDbConfig.class})
@ActiveProfiles("dev")
public class StepDefines {

  @Autowired
  ReaderService readerService;

  Reader reader;
  
  @Autowired
  DataSource ds;
  
  @Given("^a user with name Lily$")
  public void a_user_with_name_Lily() throws Throwable {
    ScriptUtils.executeSqlScript(ds.getConnection(), new ClassPathResource("test-reader-data.sql"));
    reader = readerService.findByName("Lily");
  }

  @Given("^this user Lily exists$")
  public void this_user_Lily_exists() throws Throwable {
    assertThat(reader, notNullValue());
  }

  @Then("^Lily's info should be returned$")
  public void lily_s_info_should_be_returned() throws Throwable {
    assertThat(reader.getName(), is("Lily"));
  }

}
