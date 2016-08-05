package app.config;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.WebExpressionVoter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  private static Logger logger = LoggerFactory.getLogger(SpringSecurityConfig.class);
  
  @Autowired
  private RealtimeAccessDecisionManager accessDecisionManager;

  protected void configure(HttpSecurity http) throws Exception {
    //@formatter:off
      http
          .authorizeRequests().accessDecisionManager(accessDecisionManager)         
              .anyRequest().authenticated()
              .and()
          .formLogin().and()
          .httpBasic()
          .and().csrf().disable();
    // @formatter:on
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and()
        .withUser("admin").password("password").roles("USER", "ADMIN");
  }

  @Bean
  RealtimeAccessDecisionManager accessDecisionManager(){
    logger.info("初始化AccessDecisionManager");
    return new RealtimeAccessDecisionManager(Arrays.asList(new AuthenticatedVoter(), new WebExpressionVoter()));
  }

}
