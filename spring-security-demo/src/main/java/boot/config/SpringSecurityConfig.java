package boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import boot.security.DaoUserDetailService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DaoUserDetailService daoUserDetailService;

	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.authorizeRequests()
			.antMatchers("/user/signup").permitAll()
			.anyRequest().authenticated()
			.and().formLogin()
			.and().httpBasic()
			.and().csrf().disable();		
		// @formatter:on
	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(new StandardPasswordEncoder());
		daoAuthenticationProvider.setUserDetailsService(daoUserDetailService);
		daoAuthenticationProvider.afterPropertiesSet();
		auth.authenticationProvider(daoAuthenticationProvider);

	}

	public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	}

}
