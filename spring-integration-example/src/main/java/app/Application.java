package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@ComponentScan
public class Application {

  @Autowired
  private ConfigurableEnvironment env;

  public void profile(){    
    env.setActiveProfiles("dev");
  }
}
